package com.dicoding.jpsubsatu.moviedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jpsubsatu.R
import com.dicoding.jpsubsatu.data.MovieEntity
import com.dicoding.jpsubsatu.databinding.ActivityMovieDetailBinding
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

        val viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[DetailMovieViewModel::class.java]
        val extras = intent.extras
        if (extras != null)
        {
            val movieId = extras.getString(EXTRA_MOVIE)
            if(movieId != null)
            {
                viewModel.setSelectedMovie(movieId)
                populateMovie(viewModel.getMovie())
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

}