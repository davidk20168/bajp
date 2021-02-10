package com.dicoding.jpsubdua.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.jpsubdua.data.MovieEntity
import com.dicoding.jpsubdua.data.TvshowEntity
import com.dicoding.jpsubdua.data.source.remote.RemoteDataSource
import com.dicoding.jpsubdua.data.source.remote.response.MovieResponse
import com.dicoding.jpsubdua.data.source.remote.response.TvshowResponse

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource) : MovieDataSource {
    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteData : RemoteDataSource) : MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData)
            }
    }

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponses) {
                    val movie = MovieEntity(response.movieId,
                        response.title,
                        response.category,
                        response.synopsis,
                        response.imagePoster
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        }
        )
        return movieResults
    }

    override fun getAllTvshows(): LiveData<List<TvshowEntity>> {
        val tvshowResults = MutableLiveData<List<TvshowEntity>>()
        remoteDataSource.getAllTvshows(object : RemoteDataSource.LoadTvshowsCallback {
            override fun onAllTvshowsReceived(tvshowResponses: List<TvshowResponse>) {
                val tvshowList = ArrayList<TvshowEntity>()
                for (response in tvshowResponses) {
                    val tvshow = TvshowEntity(response.tvshowId,
                        response.title,
                        response.category,
                        response.synopsis,
                        response.imagePoster
                    )
                    tvshowList.add(tvshow)
                }
                tvshowResults.postValue(tvshowList)
            }
        }
        )
        return tvshowResults
    }

    override fun getContentMovie(movieId: String): LiveData<MovieEntity> {
        val movieContentResult = MutableLiveData<MovieEntity>()
        remoteDataSource.getMovieContent(movieId, object : RemoteDataSource.LoadContentMovieCallback {
            override fun onContentReceived(movieResponse: MovieResponse) {
                lateinit var movie: MovieEntity
                if(movieResponse.movieId == movieId)
                {
                    movie = MovieEntity(movieResponse.movieId,
                        movieResponse.title,
                        movieResponse.category,
                        movieResponse.synopsis,
                        movieResponse.imagePoster
                    )
                }
                  movieContentResult.postValue(movie)
            }
        }
        )
        return movieContentResult
    }

    override fun getContentTvshow(tvshowId: String) : LiveData<TvshowEntity>
    {
        val tvshowContentResult = MutableLiveData<TvshowEntity>()
        remoteDataSource.getTvshowContent(tvshowId, object : RemoteDataSource.LoadContentTvshowCallback {
            override fun onContentReceived(tvshowResponse: TvshowResponse) {
                lateinit var tvshow : TvshowEntity
                if(tvshowResponse.tvshowId == tvshowId)
                {
                    tvshow = TvshowEntity(tvshowResponse.tvshowId,
                        tvshowResponse.title,
                        tvshowResponse.category,
                        tvshowResponse.synopsis,
                        tvshowResponse.imagePoster
                    )
                }
                tvshowContentResult.postValue(tvshow)
            }
        }
        )
        return tvshowContentResult
    }
}