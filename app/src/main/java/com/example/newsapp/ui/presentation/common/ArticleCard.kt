package com.example.newsapp.ui.presentation.common

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.Source
import com.example.newsapp.ultils.TimeConverter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick:() -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .clickable(
                onClick = onClick,
                onClickLabel = "Read the full content of this article"
            ),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(Color.White)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier.semantics{
                    contentDescription = "Article heading ${article.title}"
                }
            )

            Text(
                text = article.description,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.Black
                ),
                modifier = Modifier.semantics{
                    contentDescription = "Short description of the article ${article.description}"
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.Black
                    )
                    ,
                    modifier = Modifier.semantics{
                        contentDescription = "From ${article.source.name}"
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Gray
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = TimeConverter.formatIsoTime(article.publishedAt),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.Gray
                    ),
                    modifier = Modifier.semantics{
                        contentDescription = "Published at" + TimeConverter.formatIsoTime(article.publishedAt)
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(0.9f),
                thickness = 4.dp
            )
        }
    }
}