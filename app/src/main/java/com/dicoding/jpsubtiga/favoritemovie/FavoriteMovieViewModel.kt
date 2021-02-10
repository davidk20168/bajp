package com.dicoding.jpsubtiga.favoritemovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.jpsubtiga.data.source.local.entity.MovieEntity
import com.dicoding.jpsubtiga.data.source.MovieRepository

class FavoriteMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovieBookmarks() : LiveData<PagedList<MovieEntity>> = movieRepository.getBookmarkedMovies()
}

