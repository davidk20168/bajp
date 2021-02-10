package com.dicoding.jpsubdua.data.source.remote

import android.os.Handler
import com.dicoding.jpsubdua.data.source.remote.response.MovieResponse
import com.dicoding.jpsubdua.data.source.remote.response.TvshowResponse
import com.dicoding.jpsubdua.utils.EspressoIdlingResource
import com.dicoding.jpsubdua.utils.JsonHelper

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

    /*
        fun getAllCourses(callback: LoadCoursesCallback) {
        handler.postDelayed({ callback.onAllCoursesReceived(jsonHelper.loadCourses()) }, SERVICE_LATENCY_IN_MILLIS)
    }
     */

    fun getAllMovies(callback : LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllMoviesReceived(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
                            }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllTvshows(callback: LoadTvshowsCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllTvshowsReceived(jsonHelper.loadTvshows())
            EspressoIdlingResource.decrement()
                            }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getMovieContent(movieId: String, callback : LoadContentMovieCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onContentReceived(jsonHelper.loadContentMovie(movieId))
            EspressoIdlingResource.decrement()
                            }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getTvshowContent(tvshowId: String, callback: LoadContentTvshowCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onContentReceived(jsonHelper.loadContentTvshow(tvshowId))
            EspressoIdlingResource.decrement()
                            }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponses: List<MovieResponse>)
    }

    interface LoadTvshowsCallback {
        fun onAllTvshowsReceived(tvshowResponses: List<TvshowResponse>)
    }

    interface LoadContentMovieCallback {
        fun onContentReceived(movieResponse: MovieResponse)
    }

    interface LoadContentTvshowCallback {
        fun onContentReceived(tvshowResponse: TvshowResponse)
    }



}