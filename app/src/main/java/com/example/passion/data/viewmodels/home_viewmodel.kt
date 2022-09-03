package com.example.passion.data.viewmodels

import androidx.lifecycle.*
import com.example.passion.data.models.Articles
import com.example.passion.data.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    var articleList: LiveData<List<Articles>> = repository.articleList

    var loadingStatus: LiveData<Boolean> = repository.loadingStatus

    init {
        getArticles()
    }

    private fun getArticles() {
        viewModelScope.launch {
            repository.getNewsArticles()
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: HomeRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repository) as T
        }
    }
}


