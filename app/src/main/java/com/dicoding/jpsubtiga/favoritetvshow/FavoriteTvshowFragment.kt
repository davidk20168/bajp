package com.dicoding.jpsubtiga.favoritetvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jpsubtiga.databinding.FragmentFavoriteTvshowBinding
import com.dicoding.jpsubtiga.viewmodel.ViewModelFactory


class FavoriteTvshowFragment : Fragment() {
    private lateinit var fragmentFavoriteTvshowFragment: FragmentFavoriteTvshowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentFavoriteTvshowFragment = FragmentFavoriteTvshowBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteTvshowFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null)
        {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this,factory)[FavoriteTvshowViewModel::class.java]

            val favoriteTvshowAdapter = FavoriteTvshowAdapter()

            showLoading(true)
            viewModel.getTvshowBookmarks().observe(this, {tvshows ->
                showLoading(false)
                favoriteTvshowAdapter.setTvshows(tvshows)
                favoriteTvshowAdapter.submitList(tvshows)
                favoriteTvshowAdapter.notifyDataSetChanged()
            })

            with(fragmentFavoriteTvshowFragment.rvFavoriteListTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteTvshowAdapter
            }

        }
    }

    override fun onResume() {
        super.onResume()

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this,factory)[FavoriteTvshowViewModel::class.java]

        val favoriteTvshowAdapter = FavoriteTvshowAdapter()

        showLoading(true)
        viewModel.getTvshowBookmarks().observe(this, {tvshows ->
            showLoading(false)
            favoriteTvshowAdapter.setTvshows(tvshows)
            favoriteTvshowAdapter.submitList(tvshows)
            favoriteTvshowAdapter.notifyDataSetChanged()
        })

        with(fragmentFavoriteTvshowFragment.rvFavoriteListTvshow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoriteTvshowAdapter
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentFavoriteTvshowFragment.progressBar.visibility = View.VISIBLE
        } else {
            fragmentFavoriteTvshowFragment.progressBar.visibility = View.GONE
        }
    }
}