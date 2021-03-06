package com.example.filmlist.ui.films.film_detailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.filmlist.R
import com.example.filmlist.core.domain.models.FilmDetailed
import com.example.filmlist.databinding.FragmentFilmDetailedBinding
import com.example.filmlist.ui.MainViewModel
import com.example.filmlist.ui.utils.getHeart
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FragmentFilmDetailed private constructor(): Fragment() {
    private var _binding: FragmentFilmDetailedBinding? = null
    private val binding get() = _binding!!

    private var filmId: Int = 0
    private val flowViewModel: MainViewModel by sharedViewModel()
    private val viewModel: FilmDetailedViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmDetailedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.film.observe(viewLifecycleOwner, ::setUpFilm)
        viewModel.filmException.observe(viewLifecycleOwner) {
            // todo add error handling logic
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        }

        viewModel.getFilmDetailed(filmId)

        binding.back.setOnClickListener {
            flowViewModel.back()
        }
    }

    private fun setUpFilm(film: FilmDetailed) = with(binding) {
        var copyFilm = film.copy()

        val picasso = Picasso.get()
        if (!copyFilm.poster.isNullOrBlank()) {
            picasso
                .load(copyFilm.poster)
                .placeholder(R.drawable.pic_no_poster)
                .error(R.drawable.pic_no_poster)
                .into(binding.poster)
        } else {
            picasso.load(R.drawable.pic_no_poster_large).into(binding.poster)
        }

        binding.like.setImageResource(getHeart(copyFilm.liked))
        binding.like.setOnClickListener {
            val newState = !copyFilm.liked

            copyFilm = copyFilm.copy(liked = newState)
            binding.like.setImageResource(getHeart(newState))

            viewModel.changeFilmLike(id, newState)
        }

        binding.title.setOrGoneIfBlank(copyFilm.title)
        binding.date.setOrGoneIfBlank(copyFilm.date)
        binding.overview.setOrGoneIfBlank(copyFilm.overview)
        binding.voteAverage.setOrGoneIfBlank(copyFilm.voteAverage.toString())
    }

    private fun TextView.setOrGoneIfBlank(value: String?) {
        if (!value.isNullOrBlank()) {
            text = value
            isVisible = true
        } else {
            isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(id: Int): FragmentFilmDetailed {
            return FragmentFilmDetailed().also { it.filmId = id }
        }
    }
}
