package com.dicoding.jpsubdua.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jpsubdua.databinding.FragmentMovieBinding
import com.dicoding.jpsubdua.viewmodel.ViewModelFactory

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

            showLoading(true)
            viewModel.getMovies().observe( this, {
                movies -> showLoading(false)
                movieAdapter.setMovies(movies)
                movieAdapter.notifyDataSetChanged()
            }
            )


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