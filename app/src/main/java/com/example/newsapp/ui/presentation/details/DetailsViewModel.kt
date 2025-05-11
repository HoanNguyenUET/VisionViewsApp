package com.example.newsapp.ui.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.newsapp.NewsAppApplication
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.usescases.news.NewsUseCases
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val newsUseCases: NewsUseCases
): ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCases.getArticle(url = event.article.url).first()
                    if (article == null){
                        upsertArticle(article = event.article)
                    }else{
                        deleteArticle(article = event.article)
                    }
                }
            }
            is DetailsEvent.RemoveSideEffect ->{
                sideEffect = null
            }
        }
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article = article)
        sideEffect = "Article Unsaved"
    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCases.upsertArticle(article = article)
        sideEffect = "Article Saved"
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NewsAppApplication)
                DetailsViewModel(
                    newsUseCases = application.appContainer.NewsUseCases
                )
            }
        }
    }
}