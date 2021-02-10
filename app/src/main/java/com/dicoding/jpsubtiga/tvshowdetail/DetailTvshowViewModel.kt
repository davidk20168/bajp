package com.dicoding.jpsubtiga.tvshowdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.jpsubtiga.data.source.local.entity.TvshowEntity
import com.dicoding.jpsubtiga.data.source.MovieRepository
import com.dicoding.jpsubtiga.vo.Resource

class DetailTvshowViewModel (private val movieRepository: MovieRepository) : ViewModel() {
    val tvshowId = MutableLiveData<String>()

    fun setSelectedTvshow(tvshowId : String)
    {
        this.tvshowId.value = tvshowId
    }

    fun getTvshow() : LiveData<Resource<TvshowEntity>> = Transformations.switchMap(tvshowId) { mTvshowId ->
        movieRepository.getContentTvshow(mTvshowId) }

    fun setBookmark(tvshowEntity: TvshowEntity)
    {
        val newState = !tvshowEntity.bookmarked
        movieRepository.setTvshowBookmark(tvshowEntity,newState)
    }

}