package com.example.mybookapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mybookapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private val homeAdapter by lazy { HomeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.getBooksFromRepository()
        binding.rVHome.adapter = homeAdapter

        observeLiveData()


    }

    private fun observeLiveData() {

        homeViewModel.books.observe(viewLifecycleOwner) { books ->
            books?.let {
                binding.apply {
                    homeAdapter.updateBooksList(books)
                }
            }
        }

    }
}