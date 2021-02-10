package com.dicoding.jpsubsatu.tvshow

import androidx.lifecycle.ViewModel
import com.dicoding.jpsubsatu.data.TvshowEntity
import com.dicoding.jpsubsatu.utils.DataDummy

class TvshowViewModel : ViewModel() {
    fun getTvshows() : List<TvshowEntity> = DataDummy.generateTvshows()
}