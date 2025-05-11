package com.example.newsapp

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp.data.manager.LocalUserManagerRepository
import com.example.newsapp.domain.manager.LocalUserManager



class NewsAppApplication: Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = DefaultAppContainer(this)
    }
}