package com.dicoding.jpsubtiga.utils

import android.content.Context
import com.dicoding.jpsubtiga.data.source.remote.response.MovieResponse
import com.dicoding.jpsubtiga.data.source.remote.response.TvshowResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context : Context) {
    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies() : List<MovieResponse> {
        val list = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponses.json").toString())
            val listArray = responseObject.getJSONArray("movies")
            for (i in 0 until listArray.length())
            {
                val movie = listArray.getJSONObject(i)

                val movieId = movie.getString("id")
                val movieTitle = movie.getString("title")
                val movieCategory = movie.getString("category")
                val movieSynopsis = movie.getString("synopsis")
                val moviePoster = movie.getString("imageposter")

                val movieResponse = MovieResponse(movieId, movieTitle, movieCategory, movieSynopsis, moviePoster)
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadTvshows() : List<TvshowResponse> {
        val list = ArrayList<TvshowResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("TvshowResponses.json").toString())
            val listArray = responseObject.getJSONArray("tvshows")
            for (i in 0 until listArray.length())
            {
                val tvshow = listArray.getJSONObject(i)

                val tvshowId = tvshow.getString("id")
                val tvshowTitle = tvshow.getString("title")
                val tvshowCategory = tvshow.getString("category")
                val tvshowSynopsis = tvshow.getString("synopsis")
                val tvshowPoster = tvshow.getString("imageposter")

                val tvshowResponse = TvshowResponse(tvshowId,tvshowTitle,tvshowCategory,tvshowSynopsis,tvshowPoster)
                list.add(tvshowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadContentMovie(id : String) : MovieResponse {
        var movieResponse: MovieResponse? = null
        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponses.json").toString())
            val listArray = responseObject.getJSONArray("movies")
            for (i in 0 until listArray.length())
            {
                val movie = listArray.getJSONObject(i)
                val movieId = movie.getString("id")
                if(movieId == id) {
                    val movieTitle = movie.getString("title")
                    val movieCategory = movie.getString("category")
                    val movieSynopsis = movie.getString("synopsis")
                    val moviePoster = movie.getString("imageposter")
                    movieResponse = MovieResponse(movieId, movieTitle, movieCategory, movieSynopsis, moviePoster)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return movieResponse as MovieResponse
    }

    fun loadContentTvshow(id : String) : TvshowResponse {
        var tvshowResponse: TvshowResponse? = null
        try {
            val responseObject = JSONObject(parsingFileToString("TvshowResponses.json").toString())
            val listArray = responseObject.getJSONArray("tvshows")
            for (i in 0 until listArray.length())
            {
                val tvshow = listArray.getJSONObject(i)
                val tvshowId = tvshow.getString("id")
                if(tvshowId == id) {
                    val tvshowTitle = tvshow.getString("title")
                    val tvshowCategory = tvshow.getString("category")
                    val tvshowSynopsis = tvshow.getString("synopsis")
                    val tvshowPoster = tvshow.getString("imageposter")
                    tvshowResponse = TvshowResponse(tvshowId,tvshowTitle,tvshowCategory,tvshowSynopsis,tvshowPoster)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return tvshowResponse as TvshowResponse
    }



}