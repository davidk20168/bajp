package com.dicoding.jpsubtiga.tvshow

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
import com.dicoding.jpsubtiga.databinding.ItemRowTvshowBinding
import com.dicoding.jpsubtiga.tvshowdetail.TvshowDetailActivity

class TvshowAdapter: PagedListAdapter<TvshowEntity, TvshowAdapter.TvshowHolder>(DIFF_CALLBACK) {
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

    class TvshowHolder (private val binding: ItemRowTvshowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (tvShows: TvshowEntity) {
            with(binding)
            {
                txtTvshowTitle.text = tvShows.title

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
                    .into(imgItemTvshow)
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
        val itemRowTvshowBinding = ItemRowTvshowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TvshowHolder(itemRowTvshowBinding)
    }

    override fun onBindViewHolder(holder: TvshowHolder, position: Int) {
        val tvshows = listTvshow[position]
        holder.bind(tvshows)
    }

    override fun getItemCount(): Int = listTvshow.size
}