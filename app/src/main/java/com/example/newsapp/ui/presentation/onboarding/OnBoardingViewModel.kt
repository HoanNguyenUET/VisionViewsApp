package com.example.newsapp.ui.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.newsapp.NewsAppApplication
import com.example.newsapp.domain.usescases.app_entry.AppEntryUseCases
import kotlinx.coroutines.launch

class OnBoardingViewModel(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel() {

    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }

    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NewsAppApplication)
                OnBoardingViewModel(
                    appEntryUseCases = application.appContainer.AppEntryUseCases
                )
            }
        }
    }
}