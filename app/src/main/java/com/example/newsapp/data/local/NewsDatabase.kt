package com.example.newsapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.domain.model.Article

@Database(entities = [Article::class],version = 1,exportSchema = false)
@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        // Instance variable keeps a reference to the db when it is created
        @Volatile
        private var Instance: NewsDatabase? = null

        fun getDatabase(context: Context): NewsDatabase {
            // this is the companion object
            // use synchornized to allow only one thread to access the db instance
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, NewsDatabase::class.java, "news_database")
                    .fallbackToDestructiveMigration(false)
                    .addTypeConverter(NewsTypeConvertor())
                    .build()
                    .also { Instance = it }
            }
        }
    }

}