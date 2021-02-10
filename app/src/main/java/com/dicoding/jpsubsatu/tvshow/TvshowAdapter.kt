package com.dicoding.jpsubsatu.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jpsubsatu.R
import com.dicoding.jpsubsatu.data.TvshowEntity
import com.dicoding.jpsubsatu.databinding.ItemRowTvshowBinding
import com.dicoding.jpsubsatu.tvshowdetail.TvshowDetailActivity

class TvshowAdapter:RecyclerView.Adapter<TvshowAdapter.TvshowHolder>() {
    private var listTvshow = ArrayList<TvshowEntity>()

    class TvshowHolder (private val binding: ItemRowTvshowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (tvshows: TvshowEntity) {
            with(binding)
            {
                txtTvshowTitle.text = tvshows.title

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, TvshowDetailActivity::class.java)
                    intent.putExtra(TvshowDetailActivity.EXTRA_TVSHOW, tvshows.tvshowId)
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(tvshows.imagePoster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgItemTvshow)
            }
        }
    }

    fun setTvshows(tvshows : List<TvshowEntity>?) {
        if (tvshows==null) return
        this.listTvshow.clear()
        this.listTvshow.addAll(tvshows)
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