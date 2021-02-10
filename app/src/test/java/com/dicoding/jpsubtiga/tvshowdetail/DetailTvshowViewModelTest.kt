package com.dicoding.jpsubtiga.tvshowdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.jpsubtiga.data.source.local.entity.TvshowEntity
import com.dicoding.jpsubtiga.data.source.MovieRepository
import com.dicoding.jpsubtiga.utils.DataDummy
import com.dicoding.jpsubtiga.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvshowViewModelTest {
    private lateinit var viewModel: DetailTvshowViewModel
    private val dummyTvshow = DataDummy.generateTvshows()[0]
    private val tvshowId = dummyTvshow.tvshowId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var tvshowsObserver: Observer<Resource<TvshowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailTvshowViewModel(movieRepository)
        viewModel.setSelectedTvshow(tvshowId)
    }

    @Test
    fun getTvshow() {
        val dummyTvshow = Resource.success(DataDummy.generateTvshows()[0])
        val tvshow = MutableLiveData<Resource<TvshowEntity>>()
        tvshow.value = dummyTvshow

        `when`(movieRepository.getContentTvshow(dummyTvshow.data?.tvshowId.toString())).thenReturn(tvshow)
        viewModel.getTvshow().observeForever(tvshowsObserver)
        verify(tvshowsObserver).onChanged(dummyTvshow)
    }


}