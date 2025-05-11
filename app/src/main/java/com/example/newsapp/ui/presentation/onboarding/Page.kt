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
        title = "BÁO MỚI",
        description = "Đọc báo mọi lúc mọi nơi\nvới mobile app của Báo Mới",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "BÁO MỚI",
        description = "App Báo Mới\nthiết kế cho người khiếm thị",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "BÁO MỚI",
        description = "Nhận thông báo\nvề những tin tức mới nhất",
        image = R.drawable.onboarding3
    )
)