package com.example.filmlist.data.remote

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

private fun provideNetworkLogger(context: Context): ChuckerInterceptor {
    val chuckerCollector = ChuckerCollector(
        context = context,
        showNotification = true,
        retentionPeriod = RetentionManager.Period.ONE_HOUR
    )

    return ChuckerInterceptor.Builder(context)
        .collector(chuckerCollector)
        .alwaysReadResponseBody(true)
        .build()
}

@ExperimentalSerializationApi
fun <T> provideApi(
    clazz: Class<T>,
    baseUrl: String,
    context: Context,
): T {
    val chucker = provideNetworkLogger(context)

    val client = OkHttpClient().newBuilder()
        .addInterceptor(chucker)
        .build()

    val converterFactory = Json {
        ignoreUnknownKeys = true
    }.asConverterFactory(MediaType.parse("application/json")!!)

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(converterFactory)
        .build()
        .create(clazz)
}
