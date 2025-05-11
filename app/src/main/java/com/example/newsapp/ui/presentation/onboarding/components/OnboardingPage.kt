package com.example.newsapp.ui.presentation.onboarding.components

import android.app.Notification
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.presentation.onboarding.Page
import com.example.newsapp.ui.presentation.onboarding.pages

@Composable
fun OnboardingPage(
    modifier: Modifier = Modifier,
    isNotificationPage: Boolean,
    page: Page,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier.background(color = Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = page.title,
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = 32.dp),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            color = Color.Black
        )
        if (isNotificationPage) {
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedButton(
                border = BorderStroke(color = Color.Black, width = 2.dp),
                onClick = {}
            ) {
                Text(
                    text = "Bật nhận thông báo",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color.Black
                )
            }
        }
    }
}


@Preview
@Composable
fun OnBoardingPagePreview() {
    OnboardingPage(
        modifier = Modifier.fillMaxSize(),
        isNotificationPage = true,
        page = pages[1]
    ) { }
}