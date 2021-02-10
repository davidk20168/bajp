package com.dicoding.jpsubdua.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.jpsubdua.data.TvshowEntity
import com.dicoding.jpsubdua.data.source.MovieRepository

class TvshowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getTvshows() : LiveData<List<TvshowEntity>> = movieRepository.getAllTvshows()
}

