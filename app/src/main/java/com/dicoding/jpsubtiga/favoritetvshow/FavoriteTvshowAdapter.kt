package com.dicoding.jpsubtiga.favoritetvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jpsubtiga.R
import com.dicoding.jpsubtiga.data.source.local.entity.TvshowEntity
import com.dicoding.jpsubtiga.databinding.ItemRowFavoriteTvshowsBinding

import com.dicoding.jpsubtiga.tvshowdetail.TvshowDetailActivity

class FavoriteTvshowAdapter : PagedListAdapter<TvshowEntity, FavoriteTvshowAdapter.TvshowHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvshowEntity>() {
            override fun areItemsTheSame(oldItem: TvshowEntity, newItem: TvshowEntity): Boolean {
                return oldItem.tvshowId == newItem.tvshowId
            }
            override fun areContentsTheSame(oldItem: TvshowEntity, newItem: TvshowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }


    private var listTvshow = ArrayList<TvshowEntity>()

    inner class TvshowHolder (private val binding : ItemRowFavoriteTvshowsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (tvShows: TvshowEntity) {
            with(binding)
            {
                txtFavoriteTvshowTitle.text = tvShows.title

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, TvshowDetailActivity::class.java)
                    intent.putExtra(TvshowDetailActivity.EXTRA_TVSHOW, tvShows.tvshowId)
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(tvShows.imagePoster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgFavoriteItemTvshow)
            }
        }
    }

    fun setTvshows(tvShows : List<TvshowEntity>?) {
        if (tvShows.isNullOrEmpty()) return
        this.listTvshow.clear()
        this.listTvshow.addAll(tvShows)

        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvshowHolder {
        val itemRowFavoriteTvshowBinding = ItemRowFavoriteTvshowsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TvshowHolder(itemRowFavoriteTvshowBinding)
    }

    override fun onBindViewHolder(holder: TvshowHolder, position: Int) {
        val tvshows = listTvshow[position]
        holder.bind(tvshows)
    }

    override fun getItemCount(): Int = listTvshow.size
}