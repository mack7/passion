package com.example.passion.data.viewmodels

import androidx.lifecycle.*
import com.example.passion.data.models.Articles
import com.example.passion.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

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
}


