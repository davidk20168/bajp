package com.dicoding.jpsubdua.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.jpsubdua.FakeMovieRepository
import com.dicoding.jpsubdua.data.source.remote.RemoteDataSource
import com.dicoding.jpsubdua.utils.DataDummy
import com.dicoding.jpsubdua.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MovieRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)

    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponses[0].movieId

    private val tvshowResponses = DataDummy.generateRemoteDummyTvshows()
    private val tvshowId = tvshowResponses[0].tvshowId

    private val movieContent = DataDummy.generateRemoteDummyMovieContent(movieId)
    private val tvshowContent = DataDummy.generateRemoteDummyTvshowContent(tvshowId)



    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getAllMovies())
        verify(remote).getAllMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getAllTvshows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvshowsCallback)
                .onAllTvshowsReceived(tvshowResponses)
            null
        }.`when`(remote).getAllTvshows(any())
        val tvshowEntities = LiveDataTestUtil.getValue(movieRepository.getAllTvshows())
        verify(remote).getAllTvshows(any())
        assertNotNull(tvshowEntities)
        assertEquals(tvshowResponses.size.toLong(), tvshowEntities.size.toLong())
    }

    @Test
    fun getContentMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadContentMovieCallback)
                .onContentReceived(movieContent)
            null
        }.`when`(remote).getMovieContent(eq(movieId),any())
        val movieEntitiesContent = LiveDataTestUtil.getValue(movieRepository.getContentMovie(movieId))
        verify(remote)
            .getMovieContent(eq(movieId), any())

        assertNotNull(movieEntitiesContent)
        assertNotNull(movieEntitiesContent.movieId)
        assertNotNull(movieEntitiesContent.title)
        assertNotNull(movieEntitiesContent.category)
        assertNotNull(movieEntitiesContent.synopsis)
        assertNotNull(movieEntitiesContent.imagePoster)
        assertEquals(movieContent.movieId,movieEntitiesContent.movieId)
        assertEquals(movieContent.title,movieEntitiesContent.title)
        assertEquals(movieContent.category,movieEntitiesContent.category)
        assertEquals(movieContent.synopsis,movieEntitiesContent.synopsis)
        assertEquals(movieContent.imagePoster,movieEntitiesContent.imagePoster)
    }

    @Test
    fun getContentTvshow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadContentTvshowCallback)
                .onContentReceived(tvshowContent)
            null
        }.`when`(remote).getTvshowContent(eq(tvshowId), any())
        val tvshowEntitiesContent = LiveDataTestUtil.getValue(movieRepository.getContentTvshow(tvshowId))
        verify(remote)
            .getTvshowContent(eq(tvshowId), any())
        assertNotNull(tvshowEntitiesContent)
        assertNotNull(tvshowEntitiesContent.tvshowId)
        assertNotNull(tvshowEntitiesContent.title)
        assertNotNull(tvshowEntitiesContent.category)
        assertNotNull(tvshowEntitiesContent.synopsis)
        assertNotNull(tvshowEntitiesContent.imagePoster)
        assertEquals(tvshowContent.tvshowId,tvshowEntitiesContent.tvshowId)
        assertEquals(tvshowContent.title,tvshowEntitiesContent.title)
        assertEquals(tvshowContent.category,tvshowEntitiesContent.category)
        assertEquals(tvshowContent.synopsis,tvshowEntitiesContent.synopsis)
        assertEquals(tvshowContent.imagePoster,tvshowEntitiesContent.imagePoster)
    }
}