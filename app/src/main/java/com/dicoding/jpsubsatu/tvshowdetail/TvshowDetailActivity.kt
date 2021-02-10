package com.dicoding.jpsubsatu.tvshowdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jpsubsatu.R
import com.dicoding.jpsubsatu.data.TvshowEntity
import com.dicoding.jpsubsatu.databinding.ActivityTvshowDetailBinding
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

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailTvshowViewModel::class.java]
        val extras = intent.extras

        if (extras != null)
        {
            val tvshowId = extras.getString(EXTRA_TVSHOW)
            if(tvshowId != null)
            {
                viewModel.setSelectedTvshow(tvshowId)
                populateTvshow(viewModel.getTvshow())
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
}