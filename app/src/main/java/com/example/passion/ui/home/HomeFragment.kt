package com.example.passion.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.passion.R
import com.example.passion.data.repository.HomeRepository
import com.example.passion.data.viewmodels.HomeViewModel
import com.example.passion.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


const val apiKey = "45b441614a484a82a76cbedd754b9f40"


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var _binding: FragmentHomeBinding

    private val args: HomeFragmentArgs by navArgs()

    private val newsAdapter = NewsAdapter()

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        _binding.toolBarHome.title = args.user?.name ?: ""

        _binding.recyclerViewHome.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        homeViewModel.articleList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                _binding.recyclerViewHome.visibility = View.VISIBLE
                newsAdapter.setNewsArticles(it)
            } else _binding.recyclerViewHome.visibility = View.GONE
        }
        homeViewModel.loadingStatus.observe(viewLifecycleOwner) {
            if (it) {
                _binding.shimmerLayout.root.visibility = View.VISIBLE
                _binding.shimmerLayout.shimmerContainer.startShimmer()
            } else {
                _binding.shimmerLayout.root.visibility = View.GONE
                _binding.shimmerLayout.shimmerContainer.stopShimmer()
            }
        }
    }


}