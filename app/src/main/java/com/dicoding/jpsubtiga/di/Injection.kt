package com.dicoding.jpsubtiga.di

import android.content.Context
import com.dicoding.jpsubtiga.data.source.local.LocalDataSource
import com.dicoding.jpsubtiga.data.source.local.room.MovieDatabase
import com.dicoding.jpsubtiga.utils.AppExecutors
import com.dicoding.jpsubtiga.data.source.MovieRepository
import com.dicoding.jpsubtiga.data.source.remote.RemoteDataSource
import com.dicoding.jpsubtiga.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovieRepository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}