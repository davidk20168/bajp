package com.dicoding.jpsubtiga.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.jpsubtiga.data.source.local.MovieDao
import com.dicoding.jpsubtiga.data.source.local.entity.MovieEntity
import com.dicoding.jpsubtiga.data.source.local.entity.TvshowEntity

@Database(entities = [MovieEntity::class, TvshowEntity::class],
    version = 1,
    exportSchema = false
    )
abstract class MovieDatabase : RoomDatabase() {
    abstract  fun movieDao() : MovieDao

    companion object {
        @Volatile
        private  var INSTANCE : MovieDatabase? = null

        fun getInstance(context : Context) : MovieDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                    MovieDatabase::class.java,
                    "MoviesCatalog.db").build()
            }
    }
}