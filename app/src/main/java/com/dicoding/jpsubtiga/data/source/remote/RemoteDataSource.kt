package com.dicoding.jpsubtiga.data.source.remote

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.jpsubtiga.data.source.remote.response.MovieResponse
import com.dicoding.jpsubtiga.data.source.remote.response.TvshowResponse
import com.dicoding.jpsubtiga.utils.EspressoIdlingResource
import com.dicoding.jpsubtiga.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler()

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000


        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllMovies() : LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        handler.postDelayed({
            resultMovie.value = ApiResponse.success(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
                            }, SERVICE_LATENCY_IN_MILLIS)
        return resultMovie
    }

    fun getAllTvshows() : LiveData<ApiResponse<List<TvshowResponse>>> {
        EspressoIdlingResource.increment()
        val resultTvshow = MutableLiveData<ApiResponse<List<TvshowResponse>>>()
        handler.postDelayed({
            resultTvshow.value = ApiResponse.success(jsonHelper.loadTvshows())
            EspressoIdlingResource.decrement()
                            }, SERVICE_LATENCY_IN_MILLIS)
        return resultTvshow
    }

    fun getMovieContent(movieId : String) : LiveData<ApiResponse<MovieResponse>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<MovieResponse>>()
        handler.postDelayed({
            resultMovie.value = ApiResponse.success(jsonHelper.loadContentMovie(movieId))
            EspressoIdlingResource.decrement()
                            }, SERVICE_LATENCY_IN_MILLIS)
        return resultMovie
    }

    fun getTvshowContent(tvshowId: String) : LiveData<ApiResponse<TvshowResponse>> {
        EspressoIdlingResource.increment()
        val resultTvshow = MutableLiveData<ApiResponse<TvshowResponse>>()
        handler.postDelayed({
            resultTvshow.value = ApiResponse.success(jsonHelper.loadContentTvshow(tvshowId))
            EspressoIdlingResource.decrement()
                            }, SERVICE_LATENCY_IN_MILLIS)
        return resultTvshow
    }
}