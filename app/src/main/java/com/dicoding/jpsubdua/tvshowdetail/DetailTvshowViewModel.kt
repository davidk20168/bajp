package com.dicoding.jpsubdua.tvshowdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.jpsubdua.data.TvshowEntity
import com.dicoding.jpsubdua.data.source.MovieRepository

class DetailTvshowViewModel (private val movieRepository: MovieRepository) : ViewModel() {
    private lateinit var tvshowId : String

    fun setSelectedTvshow(tvshowId : String)
    {
        this.tvshowId = tvshowId
    }

    fun getTvshow() : LiveData<TvshowEntity> {

        val tvshow : LiveData<TvshowEntity> = movieRepository.getContentTvshow(tvshowId)

        return tvshow
    }
}