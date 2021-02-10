package com.dicoding.jpsubsatu.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jpsubsatu.databinding.FragmentTvshowBinding


class TvshowFragment : Fragment() {
    private lateinit var fragmentTvshowBinding: FragmentTvshowBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            showLoading(true)
            val viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[TvshowViewModel::class.java]

            val tvshows = viewModel.getTvshows()
            val tvshowAdapter = TvshowAdapter()
            tvshowAdapter.setTvshows(tvshows)
            with(fragmentTvshowBinding.rvListTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvshowAdapter
            }
            showLoading(false)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentTvshowBinding = FragmentTvshowBinding.inflate(layoutInflater, container, false)
        return fragmentTvshowBinding.root
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentTvshowBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentTvshowBinding.progressBar.visibility = View.GONE
        }
    }

}