package com.dicoding.jpsubdua.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jpsubdua.databinding.FragmentTvshowBinding
import com.dicoding.jpsubdua.viewmodel.ViewModelFactory


class TvshowFragment : Fragment() {
    private lateinit var fragmentTvshowBinding: FragmentTvshowBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvshowViewModel::class.java]

            val tvshowAdapter = TvshowAdapter()
            showLoading(true)
            viewModel.getTvshows().observe(this, { tvshows ->
                showLoading(false)
                tvshowAdapter.setTvshows(tvshows)
                tvshowAdapter.notifyDataSetChanged()
            }
            )


            with(fragmentTvshowBinding.rvListTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvshowAdapter
            }

        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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