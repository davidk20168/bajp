package com.dicoding.jpsubtiga.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.jpsubtiga.data.source.local.entity.MovieEntity
import com.dicoding.jpsubtiga.data.source.local.entity.TvshowEntity

class LocalDataSource private constructor(private val mMovieDao : MovieDao) {
    companion object {
        private var INSTANCE : LocalDataSource? = null

        fun getInstance(movieDao : MovieDao) : LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

    fun getAllMovies() : DataSource.Factory<Int, MovieEntity> = mMovieDao.getMovies()

    fun getAllTvshows() : DataSource.Factory<Int, TvshowEntity> = mMovieDao.getTvshows()

    fun getContentMovie(movieId : String) : LiveData<MovieEntity> = mMovieDao.getMovieById(movieId)

    fun getContentTvshow(tvshowId : String) : LiveData<TvshowEntity> = mMovieDao.getTvshowById(tvshowId)

    fun insertMovies(movies : List<MovieEntity>) = mMovieDao.insertMovies(movies)

    fun insertTvshows(tvshows : List<TvshowEntity>) = mMovieDao.insertTvshows(tvshows)

    fun updateMovies(movie : MovieEntity) = mMovieDao.updateMovies(movie)

    fun updateTvshows(tvshow : TvshowEntity) = mMovieDao.updateTvshows(tvshow)

    fun getBookmarkedMovie() : DataSource.Factory<Int, MovieEntity> = mMovieDao.getBookmarkedMovie()

    fun getBookmarkedTvshow() : DataSource.Factory<Int, TvshowEntity> = mMovieDao.getBookmarkedTvshow()

    fun setBookmarkedMovie(movie : MovieEntity, newState : Boolean) {
        movie.bookmarked = newState
        mMovieDao.updateMovies(movie)
    }

    fun setBookmarkedTvshow(tvshow : TvshowEntity, newState: Boolean) {
        tvshow.bookmarked = newState
        mMovieDao.updateTvshows(tvshow)
    }

}