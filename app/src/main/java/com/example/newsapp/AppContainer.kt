package com.example.newsapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp.data.local.NewsDatabase
import com.example.newsapp.data.manager.LocalUserManagerRepository
import com.example.newsapp.data.remote.dto.NewsApi
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.manager.LocalUserManager
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.usescases.app_entry.AppEntryUseCases
import com.example.newsapp.domain.usescases.app_entry.ReadAppEntry
import com.example.newsapp.domain.usescases.app_entry.SaveAppEntry
import com.example.newsapp.domain.usescases.news.DeleteArticle
import com.example.newsapp.domain.usescases.news.GetArticle
import com.example.newsapp.domain.usescases.news.GetArticles
import com.example.newsapp.domain.usescases.news.GetNews
import com.example.newsapp.domain.usescases.news.NewsUseCases
import com.example.newsapp.domain.usescases.news.UpsertArticle
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val LocalUserManager: LocalUserManager
    val NewsRepository: NewsRepository
    val AppEntryUseCases: AppEntryUseCases
    val NewsUseCases: NewsUseCases
}

// --- INITIALIZATION OF THE DATASTORE ---
private const val ENTRY_PREFERENCE_NAME = "entry_preference"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = ENTRY_PREFERENCE_NAME
)

class DefaultAppContainer(
    private val context: Context
) : AppContainer {

    // URL + API service
    private val baseUrl = "https://newsapi.org/v2/"

    private val retrofit1 = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: NewsApi by lazy {
        retrofit1.create(NewsApi::class.java)
    }

    // Repository
    override val LocalUserManager: LocalUserManager =
        LocalUserManagerRepository(
            dataStore = context.dataStore,
        )

    override val NewsRepository: NewsRepository by lazy {
        val database = NewsDatabase.getDatabase(context)
        NewsRepositoryImpl(
            newsApi = retrofitService,
            newsDao = database.newsDao()
        )
    }


    // Use cases
    override val AppEntryUseCases: AppEntryUseCases =
        AppEntryUseCases(
            readAppEntry = ReadAppEntry(LocalUserManager),
            saveAppEntry = SaveAppEntry(LocalUserManager)
        )

    override val NewsUseCases: NewsUseCases =
        NewsUseCases(
            getNews = GetNews(NewsRepository),
            upsertArticle = UpsertArticle(NewsRepository),
            deleteArticle = DeleteArticle(NewsRepository),
            getArticles = GetArticles(NewsRepository),
            getArticle = GetArticle(NewsRepository)
        )

}