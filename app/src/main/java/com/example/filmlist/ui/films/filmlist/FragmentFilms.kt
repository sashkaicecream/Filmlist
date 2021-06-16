package com.example.filmlist.ui.films.filmlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.filmlist.R
import com.example.filmlist.core.domain.models.Film
import com.example.filmlist.databinding.FragmentFilmsBinding
import com.example.filmlist.ui.ApplicationStage
import com.example.filmlist.ui.MainViewModel
import com.example.filmlist.ui.utils.dp
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FragmentFilms : Fragment() {
    private var _binding: FragmentFilmsBinding? = null
    private val binding get() = _binding!!

    private val flowViewModel: MainViewModel by sharedViewModel()
    private val viewModel: FilmsViewModel by viewModel()
    private var adapter: FilmsAdapter? = null

    var likeStage = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FilmsAdapter(
            onLikeClicked = { id, newState -> viewModel.changeFilmLike(id, newState) },
            onItemClicked = { id ->
                flowViewModel.changeStage(ApplicationStage.FilmDetailedStage(id))
            }
        )

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.addItemDecoration(FilmsItemDecorator(2, 15.dp, 24.dp))
        binding.recyclerView.adapter = adapter
        binding.recyclerView.itemAnimator?.changeDuration = 0


        viewModel.films.observe(viewLifecycleOwner) {
            adapter?.updateDataSet(it.map(Film::toFilmRecyclerItem))
        }

        viewModel.filmsException.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        }

        setUpButton()

        if (likeStage) viewModel.getLikedFilms() else viewModel.getFilms()
    }

    private fun setUpButton() = with(binding.navButton) {
        if (likeStage) {
            text = getString(R.string.films_button_back_txt)
            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_left)
            setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
            setOnClickListener {
                flowViewModel.changeStage(ApplicationStage.FilmsStage)
            }
        } else {
            text = getString(R.string.films_button_favourites_txt)
            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_list)
            setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
            setOnClickListener {
                flowViewModel.changeStage(ApplicationStage.LikedFilmsStage)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
