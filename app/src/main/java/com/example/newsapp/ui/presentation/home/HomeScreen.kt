package com.example.newsapp.ui.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.ui.presentation.common.ArticlesList
import com.example.newsapp.ui.presentation.navigation.Route


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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .statusBarsPadding()
    ) {
        Text(
            text = "VISION NEWS",
            style = MaterialTheme.typography.displayMedium.copy(
                color = Color.Blue
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
                .basicMarquee(), fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ArticlesList(
            modifier = Modifier.fillMaxWidth(),
            articles = articles,
            onClick = navigate
        )
    }
}