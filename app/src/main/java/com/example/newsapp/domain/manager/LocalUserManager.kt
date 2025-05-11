package com.example.newsapp.domain.manager

import com.example.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

    suspend fun saveIsFirstEntryToTrue()

    fun readIsFirstEntry(): Flow<Boolean>
}