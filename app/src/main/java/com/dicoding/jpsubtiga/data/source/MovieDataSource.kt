package com.dicoding.jpsubtiga.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.jpsubtiga.vo.Resource
import com.dicoding.jpsubtiga.data.source.local.entity.MovieEntity
import com.dicoding.jpsubtiga.data.source.local.entity.TvshowEntity

interface MovieDataSource {
    fun getAllMovies() : LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllTvshows() : LiveData<Resource<PagedList<TvshowEntity>>>

    fun getContentMovie(movieId : String) : LiveData<Resource<MovieEntity>>

    fun getContentTvshow(tvshowId : String) : LiveData<Resource<TvshowEntity>>

    fun setMovieBookmark(movie : MovieEntity, state : Boolean)

    fun setTvshowBookmark(tvshow : TvshowEntity, state : Boolean)

    fun getBookmarkedMovies(): LiveData<PagedList<MovieEntity>>

    fun getBookmarkedTvshows() : LiveData<PagedList<TvshowEntity>>

}