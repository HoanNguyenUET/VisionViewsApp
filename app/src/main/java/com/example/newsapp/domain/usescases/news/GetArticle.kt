package com.example.newsapp.domain.usescases.news

import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetArticle (
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Flow<Article?> {
        return newsRepository.getArticle(url = url)
    }
}