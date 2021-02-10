package com.dicoding.jpsubdua.moviedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jpsubdua.R
import com.dicoding.jpsubdua.data.MovieEntity
import com.dicoding.jpsubdua.databinding.ActivityMovieDetailBinding
import com.dicoding.jpsubdua.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var detailMovieBinding : ActivityMovieDetailBinding

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailMovieBinding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView((detailMovieBinding.root))

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]
        val extras = intent.extras
        if (extras != null)
        {
            val movieId = extras.getString(EXTRA_MOVIE)
            if(movieId != null)
            {
                showLoading(true)
                viewModel.setSelectedMovie(movieId)
                viewModel.getMovie().observe(this, {
                        movie -> populateMovie(movie)
                    showLoading(false)
                })

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            detailMovieBinding.progressBar.visibility = View.VISIBLE
        } else {
            detailMovieBinding.progressBar.visibility = View.GONE
        }
    }

}