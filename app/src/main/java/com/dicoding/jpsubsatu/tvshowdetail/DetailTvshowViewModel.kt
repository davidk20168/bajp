package com.dicoding.jpsubsatu.tvshowdetail

import androidx.lifecycle.ViewModel
import com.dicoding.jpsubsatu.data.TvshowEntity
import com.dicoding.jpsubsatu.utils.DataDummy

class DetailTvshowViewModel : ViewModel() {
    private lateinit var tvshowId : String

    fun setSelectedTvshow(tvshowId : String)
    {
        this.tvshowId = tvshowId
    }

    fun getTvshow() : TvshowEntity {
        lateinit var tvshow : TvshowEntity

        val tvshowEntities = DataDummy.generateTvshows()
        for (tvshowEntity in tvshowEntities)
        {
            if ( tvshowEntity.tvshowId == tvshowId)
            {
                tvshow = tvshowEntity
            }
        }

        return tvshow
    }
}