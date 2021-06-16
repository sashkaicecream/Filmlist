package com.example.filmlist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.filmlist.R
import com.example.filmlist.databinding.ActivityMainBinding
import com.example.filmlist.ui.animations.getAnimationSet
import com.example.filmlist.ui.films.film_detailed.FragmentFilmDetailed
import com.example.filmlist.ui.films.filmlist.FragmentFilms
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.stage.observe(this) { stage ->
            when(stage) {
                ApplicationStage.FilmsStage -> {
                    changeFragment(FragmentFilms(), false)
                }
                ApplicationStage.LikedFilmsStage -> {
                    val fr = FragmentFilms().also { it.likeStage = true }
                    changeFragment(fr)
                }
                is ApplicationStage.FilmDetailedStage -> {
                    val fr = FragmentFilmDetailed.newInstance(stage.id)
                    changeFragment(fr)
                }
                ApplicationStage.Cancel -> {
                    finish()
                }
            }
        }
    }

    private fun changeFragment(fragment: Fragment, backStack: Boolean = true) {
        val animationSet = getAnimationSet(viewModel.stage.value, viewModel.prev)

        supportFragmentManager.changeFragment(
            fragment = fragment,
            container = R.id.fragment_container,
            backStack = backStack,
            animSet = animationSet,
        )
    }

    override fun onBackPressed() {
        viewModel.back()
    }
}
