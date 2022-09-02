package com.example.passion.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.passion.R
import com.example.passion.databinding.FragmentHomeBinding

const val apiKey = "45b441614a484a82a76cbedd754b9f40"

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var _binding: FragmentHomeBinding

    private val args: HomeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        _binding.recyclerViewHome.apply {
            adapter = NewsAdapter()
            layoutManager = LinearLayoutManager(context)
        }
    }


}