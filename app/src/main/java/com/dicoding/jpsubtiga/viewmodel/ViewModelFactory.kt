package com.dicoding.jpsubtiga.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.jpsubtiga.data.source.MovieRepository
import com.dicoding.jpsubtiga.di.Injection
import com.dicoding.jpsubtiga.favoritemovie.FavoriteMovieViewModel
import com.dicoding.jpsubtiga.favoritetvshow.FavoriteTvshowViewModel
import com.dicoding.jpsubtiga.moviedetail.DetailMovieViewModel
import com.dicoding.jpsubtiga.movies.MovieViewModel
import com.dicoding.jpsubtiga.tvshow.TvshowViewModel
import com.dicoding.jpsubtiga.tvshowdetail.DetailTvshowViewModel

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

            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                return FavoriteMovieViewModel(mMovieRepository) as T
            }

            modelClass.isAssignableFrom(FavoriteTvshowViewModel::class.java) -> {
                return FavoriteTvshowViewModel(mMovieRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}