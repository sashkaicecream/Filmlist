package com.example.filmlist.ui.utils

import android.content.res.Resources
import com.example.filmlist.R

val Int.dp
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun getHeart(liked: Boolean) = if (liked) R.drawable.ic_heart_liked else R.drawable.ic_heart
