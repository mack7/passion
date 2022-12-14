package com.example.passion.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.passion.data.api.NewsApi
import com.example.passion.data.models.Articles
import com.example.passion.ui.home.apiKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: NewsApi) {

    val articleList: MutableLiveData<List<Articles>> = MutableLiveData()

    val loadingStatus: MutableLiveData<Boolean> = MutableLiveData()

    suspend fun getNewsArticles() {
        withContext(Dispatchers.IO) {
            loadingStatus.postValue(true)
            val result = api.getNews("us", apiKey)
            if (result.isSuccessful) {
                articleList.postValue(result.body()?.articles ?: mutableListOf<Articles>())
            }
            loadingStatus.postValue(false)
        }
    }
}