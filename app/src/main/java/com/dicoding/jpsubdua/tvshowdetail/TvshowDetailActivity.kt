package com.dicoding.jpsubdua.tvshowdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jpsubdua.R
import com.dicoding.jpsubdua.data.TvshowEntity
import com.dicoding.jpsubdua.databinding.ActivityTvshowDetailBinding
import com.dicoding.jpsubdua.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_tvshow_detail.*

class TvshowDetailActivity : AppCompatActivity() {
    private lateinit var detailTvshowBinding : ActivityTvshowDetailBinding

    companion object {
        const val EXTRA_TVSHOW = "extra_tvshow"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailTvshowBinding = ActivityTvshowDetailBinding.inflate(layoutInflater)
        setContentView(detailTvshowBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailTvshowViewModel::class.java]
        val extras = intent.extras

        if (extras != null)
        {
            val tvshowId = extras.getString(EXTRA_TVSHOW)
            if(tvshowId != null)
            {
                showLoading(true)
                viewModel.setSelectedTvshow(tvshowId)
                viewModel.getTvshow().observe(this, {
                        tvshow-> populateTvshow(tvshow)
                    showLoading(false)
                })
            }
        }
    }

    private fun populateTvshow(tvshowEntity: TvshowEntity) {
        detailTvshowBinding.txtTvshowTitle.text = tvshowEntity.title
        detailTvshowBinding.txtTvshowCategory.text = getString(R.string.label_tvshow_category_value,tvshowEntity.category)
        detailTvshowBinding.txtTvshowSynopsisValue.text = tvshowEntity.synopsis

        Glide.with(this)
                .load(tvshowEntity.imagePoster)
                .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                .into(img_item_tvshow)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            detailTvshowBinding.progressBar.visibility = View.VISIBLE
        } else {
            detailTvshowBinding.progressBar.visibility = View.GONE
        }
    }
}