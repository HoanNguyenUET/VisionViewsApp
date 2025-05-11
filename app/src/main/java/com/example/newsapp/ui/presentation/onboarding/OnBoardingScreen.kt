package com.example.newsapp.ui.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.presentation.common.NewsIconButton
import com.example.newsapp.ui.presentation.onboarding.components.OnboardingPage
import com.example.newsapp.ui.presentation.onboarding.components.PageIndicator
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent) -> Unit
) {

    val pagerState = rememberPagerState(initialPage = 0) {
        pages.size
    }

    val buttonState = remember {
        derivedStateOf {
            when(pagerState.currentPage) {
                0,1 -> Icons.Default.ArrowForward
                2 -> Icons.Default.Check
                else -> Icons.Default.ArrowForward
            }

        }
    }


    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.BottomStart
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState
        ) { index ->
            OnboardingPage(
                page = pages[index],
                isNotificationPage = index == 2,
                onClick = {}
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PageIndicator(
                modifier = Modifier.width(60.dp),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )
            NewsIconButton(
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage == 2) {
                            event(OnBoardingEvent.SaveAppEntry)
                        } else {
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage + 1
                            )
                        }
                    }
                },
                imageVector = buttonState.value
            )
        }
    }
}