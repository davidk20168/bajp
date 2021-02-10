package com.dicoding.jpsubtiga.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.jpsubtiga.data.source.local.entity.TvshowEntity
import com.dicoding.jpsubtiga.data.source.MovieRepository
import com.dicoding.jpsubtiga.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvshowViewModelTest {
    private lateinit var viewModel: TvshowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvshowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvshowEntity>

    @Before
    fun setUp() {
        viewModel = TvshowViewModel(movieRepository)
    }

    @Test
    fun getTvshows() {
        val dummyTvshows = Resource.success(pagedList)
        `when`(dummyTvshows.data?.size).thenReturn(10)
        val tvshows = MutableLiveData<Resource<PagedList<TvshowEntity>>>()
        tvshows.value = dummyTvshows

        `when`(movieRepository.getAllTvshows()).thenReturn(tvshows)
        val tvshowEntities = viewModel.getTvshows().value?.data
        verify(movieRepository).getAllTvshows()
        assertNotNull(tvshowEntities)
        assertEquals(10, tvshowEntities?.size)

        viewModel.getTvshows().observeForever(observer)
        verify(observer).onChanged(dummyTvshows)
    }

}