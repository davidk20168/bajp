package com.dicoding.jpsubtiga.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.jpsubtiga.vo.Resource
import com.dicoding.jpsubtiga.data.source.local.entity.TvshowEntity
import com.dicoding.jpsubtiga.data.source.MovieRepository

class TvshowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getTvshows() : LiveData<Resource<PagedList<TvshowEntity>>> = movieRepository.getAllTvshows()
}

