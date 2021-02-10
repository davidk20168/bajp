package com.dicoding.jpsubtiga.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jpsubtiga.R
import com.dicoding.jpsubtiga.data.source.local.entity.MovieEntity
import com.dicoding.jpsubtiga.databinding.ItemRowMoviesBinding
import com.dicoding.jpsubtiga.moviedetail.MovieDetailActivity

class MovieAdapter : PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK)  {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private var listMovies = ArrayList<MovieEntity>()

    class MovieViewHolder(private val binding: ItemRowMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie : MovieEntity)
        {
            with(binding) {
                txtMovieTitle.text = movie.title

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie.movieId)
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(movie.imagePoster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgItemMovie)

            }
        }
    }


    fun setMovies(movies : List<MovieEntity>?) {
        if (movies.isNullOrEmpty()) return
            this.listMovies.clear()
            this.listMovies.addAll(movies)

            this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemRowMoviesBinding = ItemRowMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(itemRowMoviesBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovies.size



}