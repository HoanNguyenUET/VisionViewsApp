package com.example.newsapp.domain.usescases.news

data class NewsUseCases(
    val getNews: GetNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val getArticles: GetArticles,
    val getArticle: GetArticle
)