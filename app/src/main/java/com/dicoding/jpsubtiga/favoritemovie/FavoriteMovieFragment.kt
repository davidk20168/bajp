package com.dicoding.jpsubtiga.favoritemovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jpsubtiga.databinding.FragmentFavoriteMovieBinding
import com.dicoding.jpsubtiga.viewmodel.ViewModelFactory

class FavoriteMovieFragment : Fragment() {
    private lateinit var fragmentFavoriteMovieFragment: FragmentFavoriteMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentFavoriteMovieFragment = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteMovieFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null)
        {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

            val favoriteMovieAdapter = FavoriteMovieAdapter()

            showLoading(true)
            viewModel.getMovieBookmarks().observe(this, { movies ->
                showLoading(false)
                favoriteMovieAdapter.setMovies(movies)
                favoriteMovieAdapter.submitList(movies)
                favoriteMovieAdapter.notifyDataSetChanged()
            }
            )


            with(fragmentFavoriteMovieFragment.rvFavoriteListMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = favoriteMovieAdapter
            }

        }
    }



    override fun onResume() {
        super.onResume()
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

        val favoriteMovieAdapter = FavoriteMovieAdapter()

        showLoading(true)
        viewModel.getMovieBookmarks().observe(this, { movies ->
            showLoading(false)
            favoriteMovieAdapter.setMovies(movies)
            favoriteMovieAdapter.submitList(movies)
            favoriteMovieAdapter.notifyDataSetChanged()
        }
        )


        with(fragmentFavoriteMovieFragment.rvFavoriteListMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = favoriteMovieAdapter
        }

    }




    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentFavoriteMovieFragment.progressBar.visibility = View.VISIBLE
        } else {
            fragmentFavoriteMovieFragment.progressBar.visibility = View.GONE
        }
    }

}