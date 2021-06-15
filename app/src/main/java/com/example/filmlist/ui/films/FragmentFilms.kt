package com.example.filmlist.ui.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.filmlist.databinding.FragmentFilmsBinding
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

        adapter = FilmsAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.addItemDecoration(FilmsItemDecorator(2, 15.dp, 24.dp))
        binding.recyclerView.adapter = adapter

        viewModel.films.observe(viewLifecycleOwner) {
            adapter?.updateDataSet(
        }
        viewModel.filmsException.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        }

        adapter?.updateDataSet(listOf(
            FilmRecyclerItem(
                poster = null,
                title = "test",
                year = "1234",
                liked = listOf(true, false).random()
            ),
            FilmRecyclerItem(
                poster = null,
                title = "test2",
                year = "1234",
                liked = listOf(true, false).random()
            ),
            FilmRecyclerItem(
                poster = null,
                title = "test3",
                year = "1234",
                liked = listOf(true, false).random()
            ),
            FilmRecyclerItem(
                poster = null,
                title = "test4",
                year = "1234",
                liked = listOf(true, false).random()
            ),
            FilmRecyclerItem(
                poster = null,
                title = "test5",
                year = "1234",
                liked = listOf(true, false).random()
            ),
        ))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
