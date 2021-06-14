package com.example.filmlist.ui.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.filmlist.databinding.FragmentFilmDetailedBinding
import com.example.filmlist.ui.MainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class FragmentFilmDetailed : Fragment() {
    private var _binding: FragmentFilmDetailedBinding? = null
    private val binding get() = _binding!!

    private val flowViewModel: MainViewModel by sharedViewModel()

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
