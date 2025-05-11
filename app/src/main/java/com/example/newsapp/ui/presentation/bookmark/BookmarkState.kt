package com.example.newsapp.ui.presentation.bookmark

import com.example.newsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)