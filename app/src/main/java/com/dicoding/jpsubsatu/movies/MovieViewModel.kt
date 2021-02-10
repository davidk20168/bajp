package com.dicoding.jpsubsatu.movies

import androidx.lifecycle.ViewModel
import com.dicoding.jpsubsatu.data.MovieEntity
import com.dicoding.jpsubsatu.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getMovies() : List<MovieEntity> = DataDummy.generateMovies()
}