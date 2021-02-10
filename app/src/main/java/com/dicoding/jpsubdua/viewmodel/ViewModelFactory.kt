package com.dicoding.jpsubdua.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.jpsubdua.data.source.MovieRepository
import com.dicoding.jpsubdua.di.Injection
import com.dicoding.jpsubdua.moviedetail.DetailMovieViewModel
import com.dicoding.jpsubdua.movies.MovieViewModel
import com.dicoding.jpsubdua.tvshow.TvshowViewModel
import com.dicoding.jpsubdua.tvshowdetail.DetailTvshowViewModel

class ViewModelFactory private constructor(private val mMovieRepository: MovieRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(mMovieRepository) as T
            }

            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                return DetailMovieViewModel(mMovieRepository) as T
            }

            modelClass.isAssignableFrom(TvshowViewModel::class.java) -> {
                return TvshowViewModel(mMovieRepository) as T
            }

            modelClass.isAssignableFrom(DetailTvshowViewModel::class.java) -> {
                return DetailTvshowViewModel(mMovieRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}