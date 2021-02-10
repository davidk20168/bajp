package com.dicoding.jpsubtiga.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.jpsubtiga.vo.Resource
import com.dicoding.jpsubtiga.data.source.local.entity.MovieEntity
import com.dicoding.jpsubtiga.data.source.MovieRepository

class DetailMovieViewModel (private val movieRepository: MovieRepository) : ViewModel() {
    val movieId = MutableLiveData<String>()

    fun setSelectedMovie(movieId : String)
    {
        this.movieId.value = movieId
    }

    fun getMovie() : LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) { mMovieId ->
        movieRepository.getContentMovie(mMovieId) }

    fun setBookmark(movieEntity: MovieEntity)
    {
        val newState = !movieEntity.bookmarked
        movieRepository.setMovieBookmark(movieEntity, newState)

    }
}