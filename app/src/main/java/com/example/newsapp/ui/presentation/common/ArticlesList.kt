package com.example.newsapp.ui.presentation.common

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.domain.model.Article

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick:(Article) -> Unit
) {


        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(
                count = articles.size,
            ) {
                val article = articles[it]
                ArticleCard(article = article, onClick = {onClick(article)})
            }
        }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsArticlesList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    newsArticles: List<Article>,
    onClick:(Article) -> Unit
) {

    val handlePagingResult = handlePagingResult(articles)

    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(
                count = newsArticles.size
            ) {
                val article = newsArticles[it]
                ArticleCard(article = article, onClick = {onClick(article)})
            }
            items(
                count = articles.itemCount,
            ) {
                articles[it]?.let { article ->
                    ArticleCard(article = article, onClick = {onClick(article)})
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(articles: LazyPagingItems<Article>): Boolean {

    val loadState = articles.loadState

    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)){
        repeat(10){
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}