package com.example.filmlist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.filmlist.R
import com.example.filmlist.databinding.ActivityMainBinding
import com.example.filmlist.ui.animations.getAnimationSet
import com.example.filmlist.ui.films.FragmentFilmDetailed
import com.example.filmlist.ui.films.FragmentFilms
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
            val fragment = when(stage) {
                is ApplicationStage.FilmDetailedStage -> FragmentFilmDetailed()
                ApplicationStage.FilmsStage -> FragmentFilms()
                ApplicationStage.LikedFilmsStage -> FragmentFilms().also { it.likeStage = true }
            }

            changeFragment(fragment)
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val animationSet = getAnimationSet(viewModel.stage.value, viewModel.prev)

        supportFragmentManager.changeFragment(
            fragment = fragment,
            container = R.id.fragment_container,
            backStack = true,
            animationSet = animationSet,
        )
    }
}
