package com.example.newsapp.ui.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.ui.presentation.common.ArticlesList
import com.example.newsapp.ui.presentation.common.NewsArticlesList
import com.example.newsapp.ui.presentation.navigation.Route


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigate:(Article) -> Unit
) {

    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 15) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 14))
                    .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
            } else {
                ""
            }
        }
    }

    val newsArticle = News.newsArticle

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "News Talking",
            style = MaterialTheme.typography.displaySmall.copy(
                color = colorResource(R.color.blue),
                fontWeight = FontWeight.ExtraBold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().semantics{
                contentDescription = "Welcome to News Talking"
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp)
                .basicMarquee()
                .semantics{
                    contentDescription = "Latest News: $titles"
                }, fontSize = 24.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(modifier = Modifier.fillMaxWidth(0.95f), color = Color.Gray, thickness = 2.dp)

        NewsArticlesList(
            modifier = Modifier.fillMaxWidth().background(color = Color.White),
            articles = articles,
            newsArticles = newsArticle,
            onClick = navigate
        )
    }
}