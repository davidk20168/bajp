package com.dicoding.jpsubtiga.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jpsubtiga.databinding.FragmentMovieBinding
import com.dicoding.jpsubtiga.viewmodel.ViewModelFactory
import com.dicoding.jpsubtiga.vo.Status

class MovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding : FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            val movieAdapter = MovieAdapter()
            viewModel.getMovies().observe(this, { movies ->
                if( movies != null) {
                    when (movies.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            showLoading(false)
                            movieAdapter.setMovies(movies.data)
                            movieAdapter.submitList(movies.data)
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentMovieBinding.rvListMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            val movieAdapter = MovieAdapter()

            viewModel.getMovies().observe(this, { movies ->
                if( movies != null) {
                    when (movies.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            showLoading(false)
                            movieAdapter.setMovies(movies.data)
                            movieAdapter.submitList(movies.data)
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentMovieBinding.rvListMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentMovieBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentMovieBinding.progressBar.visibility = View.GONE
        }
    }

}