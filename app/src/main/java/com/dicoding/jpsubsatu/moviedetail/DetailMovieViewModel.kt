package com.dicoding.jpsubsatu.moviedetail

import androidx.lifecycle.ViewModel
import com.dicoding.jpsubsatu.data.MovieEntity
import com.dicoding.jpsubsatu.utils.DataDummy

class DetailMovieViewModel : ViewModel() {
    private lateinit var movieId : String

    fun setSelectedMovie(movieId : String)
    {
        this.movieId = movieId
    }

    fun getMovie() : MovieEntity {
        lateinit var movie : MovieEntity
        val moviesEntities = DataDummy.generateMovies()
        for ( movieEntity in moviesEntities)
        {
            if ( movieEntity.movieId == movieId)
            {
                movie = movieEntity
            }
        }

        return movie
    }
}