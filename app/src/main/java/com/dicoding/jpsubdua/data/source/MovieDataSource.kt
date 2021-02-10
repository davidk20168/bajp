package com.dicoding.jpsubdua.data.source

import androidx.lifecycle.LiveData
import com.dicoding.jpsubdua.data.MovieEntity
import com.dicoding.jpsubdua.data.TvshowEntity

interface MovieDataSource {
    fun getAllMovies() : LiveData<List<MovieEntity>>

    fun getAllTvshows() : LiveData<List<TvshowEntity>>

    fun getContentMovie(movieId : String) : LiveData<MovieEntity>

    fun getContentTvshow(tvshowId : String) : LiveData<TvshowEntity>

}