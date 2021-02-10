package com.dicoding.jpsubtiga.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.jpsubtiga.vo.Resource
import com.dicoding.jpsubtiga.data.source.local.entity.MovieEntity
import com.dicoding.jpsubtiga.data.source.MovieRepository

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovies() : LiveData<Resource<PagedList<MovieEntity>>> = movieRepository.getAllMovies()
}

