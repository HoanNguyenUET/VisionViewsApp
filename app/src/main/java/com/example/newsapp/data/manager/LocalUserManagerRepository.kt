package com.example.newsapp.data.manager

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.domain.manager.LocalUserManager
import com.example.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class LocalUserManagerRepository(
    private val dataStore: DataStore<Preferences>
) : LocalUserManager {

    private companion object {
        val IS_FIRST_ENTRY = booleanPreferencesKey("is_first_entry")
        const val TAG = "LocalUserManagerRepository"
    }

    override suspend fun saveIsFirstEntryToTrue() {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[IS_FIRST_ENTRY] = true
        }
    }

    override fun readIsFirstEntry(): Flow<Boolean> {
        return dataStore.data
            .catch {
                if (it is IOException) {
                    Log.e(TAG, "ERROR reading preferences")
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map { preferences ->
                preferences[IS_FIRST_ENTRY] ?: false
            }
    }
}