package com.example.newsapp.ui.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.newsapp.NewsAppApplication
import com.example.newsapp.domain.usescases.news.NewsUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class BookmarkViewModel(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(BookmarkState())
    val state: StateFlow<BookmarkState> = _state.asStateFlow()

    init {
        getArticles()
    }

    private fun getArticles() {
        viewModelScope.launch {
            newsUseCases.getArticles().onEach { it ->
                _state.value = _state.value.copy(articles = it.asReversed())
            }.collect()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NewsAppApplication)
                BookmarkViewModel(
                    newsUseCases = application.appContainer.NewsUseCases
                )
            }
        }
    }
}