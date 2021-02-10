package com.dicoding.jpsubtiga.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.jpsubtiga.data.NetworkBoundResource
import com.dicoding.jpsubtiga.data.source.local.LocalDataSource
import com.dicoding.jpsubtiga.data.source.remote.ApiResponse
import com.dicoding.jpsubtiga.utils.AppExecutors
import com.dicoding.jpsubtiga.vo.Resource
import com.dicoding.jpsubtiga.data.source.local.entity.MovieEntity
import com.dicoding.jpsubtiga.data.source.local.entity.TvshowEntity
import com.dicoding.jpsubtiga.data.source.remote.RemoteDataSource
import com.dicoding.jpsubtiga.data.source.remote.response.MovieResponse
import com.dicoding.jpsubtiga.data.source.remote.response.TvshowResponse

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
    ) : MovieDataSource {
    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteData : RemoteDataSource, localData : LocalDataSource, appExecutors: AppExecutors) : MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors)
        {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            public override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(response.movieId,
                        response.title,
                        response.category,
                        response.synopsis,
                        response.imagePoster
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getAllTvshows(): LiveData<Resource<PagedList<TvshowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvshowEntity>, List<TvshowResponse>>(appExecutors)
        {
            public override fun loadFromDB(): LiveData<PagedList<TvshowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvshows(), config).build()

            }

            override fun shouldFetch(data: PagedList<TvshowEntity>?): Boolean =
                data==null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvshowResponse>>> =
                remoteDataSource.getAllTvshows()

            public override fun saveCallResult(data: List<TvshowResponse>) {
                val tvshowList = ArrayList<TvshowEntity>()
                for (response in data) {
                    val tvshow = TvshowEntity(response.tvshowId,
                        response.title,
                        response.category,
                        response.synopsis,
                        response.imagePoster
                    )
                    tvshowList.add(tvshow)
                }
                localDataSource.insertTvshows(tvshowList)
            }
        }.asLiveData()
    }

    override fun getContentMovie(movieId: String): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity,MovieResponse>(appExecutors)
        {
            public override fun loadFromDB(): LiveData<MovieEntity> = localDataSource.getContentMovie(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean = data?.movieId==null

            public override fun createCall(): LiveData<ApiResponse<MovieResponse>> = remoteDataSource.getMovieContent(movieId)

            public override fun saveCallResult(data: MovieResponse) {
                lateinit var movie: MovieEntity
                if(data.movieId == movieId)
                {
                    movie = MovieEntity(data.movieId,
                        data.title,
                        data.category,
                        data.synopsis,
                        data.imagePoster
                    )
                }
                localDataSource.updateMovies(movie)
            }
        }.asLiveData()
    }

    override fun getContentTvshow(tvshowId: String) : LiveData<Resource<TvshowEntity>>
    {
        return object : NetworkBoundResource<TvshowEntity,TvshowResponse>(appExecutors)
        {
            override fun loadFromDB(): LiveData<TvshowEntity> = localDataSource.getContentTvshow(tvshowId)

            override fun shouldFetch(data: TvshowEntity?): Boolean = data?.tvshowId==null

            override fun createCall(): LiveData<ApiResponse<TvshowResponse>> = remoteDataSource.getTvshowContent(tvshowId)

            override fun saveCallResult(data: TvshowResponse) {
                lateinit var tvshow : TvshowEntity
                if(data.tvshowId == tvshowId)
                {
                    tvshow = TvshowEntity(data.tvshowId,
                        data.title,
                        data.category,
                        data.synopsis,
                        data.imagePoster
                    )
                }
                localDataSource.updateTvshows(tvshow)
            }

        }.asLiveData()
    }

    override fun setMovieBookmark(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setBookmarkedMovie(movie, state) }

    override fun setTvshowBookmark(tvshow: TvshowEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setBookmarkedTvshow(tvshow, state) }

    override fun getBookmarkedMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkedMovie(), config).build()

    }

    override fun getBookmarkedTvshows(): LiveData<PagedList<TvshowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkedTvshow(), config).build()
    }
}