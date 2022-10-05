package com.axel.healthjoychallenge.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.axel.healthjoychallenge.data.datasource.rest.entities.PostRestEntity
import com.axel.healthjoychallenge.databinding.ItemPostBinding
import com.bumptech.glide.Glide


class PostPagingAdapter : PagingDataAdapter<PostRestEntity, PostPagingAdapter.PostViewHolder>(COMPARATOR) {

    private lateinit var context: Context

    inner class PostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            with(holder.binding) {

                llUserInfo.visibility = if (item.user != null) View.VISIBLE else View.GONE
                tvDescription.visibility = if (item.user != null) View.VISIBLE else View.GONE

                item.user?.username.let {
                    tvUser.text = it
                }
                item.user?.description.let {
                    tvDescription.text = it
                }

                Glide.with(context)
                    .load(item.user?.avatarUrl)
                    .centerCrop()
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .into(ivAvatar)

                Glide.with(context)
                    .load(item.images.original.url)
                    .centerCrop()
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .into(ivImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        context = parent.context
        val vh = ItemPostBinding.inflate(LayoutInflater.from(context), parent, false)
        return PostViewHolder(vh)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<PostRestEntity>() {
            override fun areItemsTheSame(oldItem: PostRestEntity, newItem: PostRestEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PostRestEntity, newItem: PostRestEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
}