package com.dicoding.jpsubdua.di

import android.content.Context
import com.dicoding.jpsubdua.data.source.MovieRepository
import com.dicoding.jpsubdua.data.source.remote.RemoteDataSource
import com.dicoding.jpsubdua.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovieRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return MovieRepository.getInstance(remoteDataSource)
    }
}