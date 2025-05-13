package com.example.newsapp.ui.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf<Page>(
    Page(
        title = "NEWS TALKING",
        description = "Read everywhere everytime\nwith News Talking mobile app",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "NEWS TALKING",
        description = "News Talking\nis specially designed\nfor visually impaired people",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "NEWS TALKING",
        description = "Notifications for\nthe hottest news and trending",
        image = R.drawable.onboarding3
    )
)