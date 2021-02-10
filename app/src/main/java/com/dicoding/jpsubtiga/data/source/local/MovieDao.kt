package com.dicoding.jpsubtiga.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.jpsubtiga.data.source.local.entity.MovieEntity
import com.dicoding.jpsubtiga.data.source.local.entity.TvshowEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieentities")
    fun getMovies() : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvshowentities")
    fun getTvshows() : DataSource.Factory<Int, TvshowEntity>

    @Query("SELECT * FROM movieentities WHERE movieId = :movieId")
    fun getMovieById(movieId: String): LiveData<MovieEntity>

    @Query("SELECT * FROM tvshowentities WHERE tvshowId= :tvshowId")
    fun getTvshowById(tvshowId : String) : LiveData<TvshowEntity>

    @Query("SELECT * FROM movieentities WHERE bookmarked = 1")
    fun getBookmarkedMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvshowentities WHERE bookmarked = 1")
    fun getBookmarkedTvshow() : DataSource.Factory<Int, TvshowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies (movies : List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvshows (tvshows : List<TvshowEntity>)

    @Update
    fun updateMovies(movie : MovieEntity)

    @Update
    fun updateTvshows(tvshow : TvshowEntity)

    @Delete
    fun deleteMovie(movie : MovieEntity)

    @Delete
    fun deleteTvshow(tvshow : TvshowEntity)

}