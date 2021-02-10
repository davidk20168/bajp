package com.dicoding.jpsubtiga.moviedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jpsubtiga.R
import com.dicoding.jpsubtiga.data.source.local.entity.MovieEntity
import com.dicoding.jpsubtiga.databinding.ActivityMovieDetailBinding
import com.dicoding.jpsubtiga.viewmodel.ViewModelFactory
import com.dicoding.jpsubtiga.vo.Status
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var detailMovieBinding : ActivityMovieDetailBinding
    private lateinit var viewModel : DetailMovieViewModel
    private var menuItem: Menu? = null

    private lateinit var movieEntity: MovieEntity

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        detailMovieBinding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView((detailMovieBinding.root))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]
        val extras = intent.extras
        if (extras != null)
        {
            val movieId = extras.getString(EXTRA_MOVIE)
            if(movieId != null)
            {
                viewModel.setSelectedMovie(movieId)

                viewModel.getMovie().observe(this, { movie ->
                    when (movie.status)
                    {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> if (movie.data != null)
                                        {
                                            showLoading(false)
                                            movieEntity = movie.data
                                            populateMovie(movie.data)
                                        }
                        Status.ERROR -> {
                                            Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                                        }
                    }
                }
                )
            }
        }
    }

    private fun populateMovie(movieEntity: MovieEntity)
    {
        detailMovieBinding.txtMovieTitle.text = movieEntity.title
        detailMovieBinding.txtMovieCategory.text = getString(R.string.label_movie_category_value,movieEntity.category)
        detailMovieBinding.txtMovieSynopsisValue.text = movieEntity.synopsis

        Glide.with(this)
                .load(movieEntity.imagePoster)
                .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                .into(img_item_movie)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu
        viewModel.getMovie().observe(this, { movie ->
            if(movie != null)
            {
                when (movie.status) {
                    Status.LOADING -> {
                        showLoading(true)
                    }
                    Status.SUCCESS -> if(movie.data != null)
                                {
                                    showLoading(false)
                                    val state = movie.data.bookmarked
                                    setBookmarkState(state)
                                }
                    Status.ERROR ->
                                {
                                    showLoading(false)
                                    Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                                }
                }
            }
            }
        )
        return true
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            detailMovieBinding.progressBar.visibility = View.VISIBLE
        } else {
            detailMovieBinding.progressBar.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        return when (item.itemId)
        {
            android.R.id.home ->
            {
                finish()
                return super.onOptionsItemSelected(item)
            }
            R.id.action_bookmark ->
            {
                viewModel.setBookmark(movieEntity)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setBookmarkState(state: Boolean) {
        if (menuItem == null) return
        val menu = menuItem?.findItem(R.id.action_bookmark)
        menu?.icon = ContextCompat.getDrawable(this, if(state) R.drawable.ic_bookmarked_white else R.drawable.ic_bookmark_white)
    }

}