package com.dicoding.jpsubtiga.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jpsubtiga.databinding.FragmentTvshowBinding
import com.dicoding.jpsubtiga.viewmodel.ViewModelFactory
import com.dicoding.jpsubtiga.vo.Status


class TvshowFragment : Fragment() {
    private lateinit var fragmentTvshowBinding: FragmentTvshowBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvshowViewModel::class.java]

            val tvshowAdapter = TvshowAdapter()

            viewModel.getTvshows().observe(this, { tvshows ->
                if (tvshows != null)
                {
                    when (tvshows.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            showLoading(false)
                            tvshowAdapter.setTvshows(tvshows.data)
                            tvshowAdapter.submitList(tvshows.data)
                            tvshowAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()

                        }
                    }
                }

            }
                )

            with(fragmentTvshowBinding.rvListTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvshowAdapter
            }

        }
    }

    override fun onResume() {
        super.onResume()

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvshowViewModel::class.java]

            val tvshowAdapter = TvshowAdapter()

            viewModel.getTvshows().observe(this, { tvshows ->
                if (tvshows != null)
                {
                    when (tvshows.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            showLoading(false)
                            tvshowAdapter.setTvshows(tvshows.data)
                            tvshowAdapter.submitList(tvshows.data)
                            tvshowAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()

                        }
                    }
                }

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