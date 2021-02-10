package com.dicoding.jpsubtiga.tvshowdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jpsubtiga.R
import com.dicoding.jpsubtiga.data.source.local.entity.TvshowEntity
import com.dicoding.jpsubtiga.databinding.ActivityTvshowDetailBinding
import com.dicoding.jpsubtiga.viewmodel.ViewModelFactory
import com.dicoding.jpsubtiga.vo.Status
import kotlinx.android.synthetic.main.activity_tvshow_detail.*

class TvshowDetailActivity : AppCompatActivity() {
    private lateinit var detailTvshowBinding : ActivityTvshowDetailBinding
    private lateinit var viewModel : DetailTvshowViewModel
    private var menuItem: Menu? = null

    private lateinit var tvshowEntity: TvshowEntity

    companion object {
        const val EXTRA_TVSHOW = "extra_tvshow"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailTvshowBinding = ActivityTvshowDetailBinding.inflate(layoutInflater)
        setContentView(detailTvshowBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailTvshowViewModel::class.java]
        val extras = intent.extras

        if (extras != null)
        {
            val tvshowId = extras.getString(EXTRA_TVSHOW)


            if(tvshowId != null)
            {
                viewModel.setSelectedTvshow(tvshowId)

                viewModel.getTvshow().observe(this, { tvshow ->
                    if (tvshow != null)
                    {
                        when (tvshow.status) {
                            Status.LOADING -> showLoading(true)
                            Status.SUCCESS->  if (tvshow.data != null)
                            {
                                showLoading(false)
                                tvshowEntity = tvshow.data
                                populateTvshow(tvshowEntity)
                            }
                            Status.ERROR ->  {
                                showLoading(false)
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu
        viewModel.getTvshow().observe(this, { tvshow ->
            if(tvshow != null)
            {
                when (tvshow.status) {
                    Status.LOADING -> {
                        showLoading(true)
                    }
                    Status.SUCCESS -> if(tvshow.data != null)
                    {
                        showLoading(false)
                        val state = tvshow.data.bookmarked
                        setBookmarkState(state)
                    }
                    Status.ERROR ->
                    {
                        showLoading(false)
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        )
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailTvshowViewModel::class.java]

        return when (item.itemId)
        {
                android.R.id.home ->
                {
                    finish()
                    return super.onOptionsItemSelected(item)
                }
                R.id.action_bookmark ->
                {
                    viewModel.setBookmark(tvshowEntity)
                    return true
                }
                else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            detailTvshowBinding.progressBar.visibility = View.VISIBLE
        } else {
            detailTvshowBinding.progressBar.visibility = View.GONE
        }
    }

    private fun setBookmarkState(state: Boolean) {
        if (menuItem == null) return
        val menu = menuItem?.findItem(R.id.action_bookmark)
        if (state) {
            menu?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmarked_white)
        } else {
            menu?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white)
        }
    }
}