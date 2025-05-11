package com.example.newsapp


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.newsapp.domain.usescases.app_entry.AppEntryUseCases
import com.example.newsapp.ui.presentation.navigation.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel() {

//    val startDestination: StateFlow<String> =
//        appEntryUseCases.readAppEntry().map {
//            if (it) {
//                Route.NewsNavigation.route
//            } else {
//                Route.AppStartNavigation.route
//            }
//        }.stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5_000),
//            initialValue = Route.AppStartNavigation.route
//        )


    private val _startDestination = mutableStateOf(Route.AppStartNavigation.route)
    val startDestination: State<String> = _startDestination

    init {
        appEntryUseCases.readAppEntry().onEach { isStartFromHomeScreen ->
                _startDestination.value =
                    if (isStartFromHomeScreen) {
                        Route.NewsNavigation.route
                    } else {
                        Route.AppStartNavigation.route
                    }
                delay(300) // add some delay to avoid the onBoarding screen will show
            }
            .launchIn(viewModelScope)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NewsAppApplication)
                MainViewModel(
                    appEntryUseCases = application.appContainer.AppEntryUseCases
                )
            }
        }
    }
}