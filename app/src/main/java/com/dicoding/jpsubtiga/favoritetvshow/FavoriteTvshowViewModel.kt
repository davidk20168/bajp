package com.dicoding.jpsubtiga.favoritetvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.jpsubtiga.data.source.local.entity.TvshowEntity
import com.dicoding.jpsubtiga.data.source.MovieRepository

class FavoriteTvshowViewModel (private val movieRepository: MovieRepository) : ViewModel() {
    fun getTvshowBookmarks() : LiveData<PagedList<TvshowEntity>> = movieRepository.getBookmarkedTvshows()
}
