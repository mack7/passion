package com.example.passion.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.passion.R
import com.example.passion.data.api.NewsApi
import com.example.passion.data.api.RetrofitClient
import com.example.passion.data.models.Articles
import com.example.passion.databinding.FragmentHomeBinding
import kotlinx.coroutines.*

const val apiKey = "45b441614a484a82a76cbedd754b9f40"

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var _binding: FragmentHomeBinding

    private val args: HomeFragmentArgs by navArgs()

    val newsAdapter = NewsAdapter()

    var articleList = MutableLiveData<List<Articles>>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        _binding.toolBarHome.title = args.user?.name ?: ""

        _binding.recyclerViewHome.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                articleList.value = getNewsArticles()
            }
        }
        articleList.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty())
                newsAdapter.setNewsArticles(it)
        })
    }

    private suspend fun getNewsArticles(): List<Articles> {
        val api = RetrofitClient.getInstance().create(NewsApi::class.java)
        val result = api.getNews("us", apiKey)
        return (result.body()?.articles ?: mutableListOf<Articles>())
    }


}