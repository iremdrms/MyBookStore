package com.example.mybookapp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import com.example.mybookapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchViewModel: SearchViewModel
    private val searchAdapter by lazy { SearchAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        //searchViewModel.getBooksFromRepository()

        binding.recyclerSearch.adapter = searchAdapter

        binding.tietSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.tietSearch.text.toString()
                if (query.isNotBlank()) {
                    searchViewModel.getBooksFromRepository(query)
                }
                true
            } else {
                false
            }
        }

        observedData()

    }

    private fun observedData() {
        searchViewModel.books.observe(viewLifecycleOwner) { books ->
            books?.let {
                binding.apply {
                    searchAdapter.updateBooksList(books)
                }
            }

        }
    }

}