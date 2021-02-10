package com.dicoding.jpsubtiga.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.jpsubtiga.FakeMovieRepository
import com.dicoding.jpsubtiga.data.source.local.entity.MovieEntity
import com.dicoding.jpsubtiga.data.source.local.entity.TvshowEntity
import com.dicoding.jpsubtiga.data.source.local.LocalDataSource
import com.dicoding.jpsubtiga.data.source.remote.RemoteDataSource
import com.dicoding.jpsubtiga.utils.AppExecutors
import com.dicoding.jpsubtiga.utils.DataDummy
import com.dicoding.jpsubtiga.utils.LiveDataTestUtil
import com.dicoding.jpsubtiga.utils.PagedListUtil
import com.dicoding.jpsubtiga.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val remote = mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote,local,appExecutors)

    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponses[0].movieId

    private val tvshowResponses = DataDummy.generateRemoteDummyTvshows()
    private val tvshowId = tvshowResponses[0].tvshowId

    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        movieRepository.getAllMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvshows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvshowEntity>
        `when`(local.getAllTvshows()).thenReturn(dataSourceFactory)
        movieRepository.getAllTvshows()

        val tvshowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateTvshows()))
        verify(local).getAllTvshows()
        assertNotNull(tvshowEntities)
        assertEquals(tvshowResponses.size.toLong(), tvshowEntities.data?.size?.toLong())
    }

    @Test
    fun getContentMovie() {
        val dummyMovieEntity = MutableLiveData<MovieEntity>()
        dummyMovieEntity.value = DataDummy.generateMovies()[0]
        `when`(local.getContentMovie(movieId)).thenReturn(dummyMovieEntity)

        val movieEntitiesContent = LiveDataTestUtil.getValue(movieRepository.getContentMovie(movieId))
        verify(local).getContentMovie(movieId)
        assertNotNull(movieEntitiesContent)
        assertNotNull(movieEntitiesContent.data?.movieId)
        assertNotNull(movieEntitiesContent.data?.title)
        assertNotNull(movieEntitiesContent.data?.category)
        assertNotNull(movieEntitiesContent.data?.synopsis)
        assertNotNull(movieEntitiesContent.data?.imagePoster)
        assertEquals(dummyMovieEntity.value?.movieId,movieEntitiesContent.data?.movieId)
        assertEquals(dummyMovieEntity.value?.title,movieEntitiesContent.data?.title)
        assertEquals(dummyMovieEntity.value?.category,movieEntitiesContent.data?.category)
        assertEquals(dummyMovieEntity.value?.synopsis,movieEntitiesContent.data?.synopsis)
        assertEquals(dummyMovieEntity.value?.imagePoster,movieEntitiesContent.data?.imagePoster)
    }

    @Test
    fun getContentTvshow() {
        val dummyTvshowEntity = MutableLiveData<TvshowEntity>()
        dummyTvshowEntity.value = DataDummy.generateTvshows()[0]
        `when`(local.getContentTvshow(tvshowId)).thenReturn(dummyTvshowEntity)

        val tvshowEntitiesContent = LiveDataTestUtil.getValue(movieRepository.getContentTvshow(tvshowId))
        verify(local).getContentTvshow(tvshowId)
        assertNotNull(tvshowEntitiesContent)
        assertNotNull(tvshowEntitiesContent.data?.tvshowId)
        assertNotNull(tvshowEntitiesContent.data?.title)
        assertNotNull(tvshowEntitiesContent.data?.category)
        assertNotNull(tvshowEntitiesContent.data?.synopsis)
        assertNotNull(tvshowEntitiesContent.data?.imagePoster)
        assertEquals(dummyTvshowEntity.value?.tvshowId,tvshowEntitiesContent.data?.tvshowId)
        assertEquals(dummyTvshowEntity.value?.title,tvshowEntitiesContent.data?.title)
        assertEquals(dummyTvshowEntity.value?.category,tvshowEntitiesContent.data?.category)
        assertEquals(dummyTvshowEntity.value?.synopsis,tvshowEntitiesContent.data?.synopsis)
        assertEquals(dummyTvshowEntity.value?.imagePoster,tvshowEntitiesContent.data?.imagePoster)
    }

    @Test
    fun getBookmarkedMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getBookmarkedMovie()).thenReturn(dataSourceFactory)
        movieRepository.getBookmarkedMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateMovies()))
        verify(local).getBookmarkedMovie()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getBookmarkedTvshows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvshowEntity>
        `when`(local.getBookmarkedTvshow()).thenReturn(dataSourceFactory)
        movieRepository.getBookmarkedTvshows()
        val tvshowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateTvshows()))

        verify(local).getBookmarkedTvshow()
        assertNotNull(tvshowEntities)
        assertEquals(tvshowResponses.size.toLong(), tvshowEntities.data?.size?.toLong())
    }

}