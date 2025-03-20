package com.github.reposview.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.reposview.R
import com.github.reposview.databinding.ItemRepositoryBinding
import com.github.reposview.domain.model.RepositoryModel

class ListRepositoriesAdapter :
    ListAdapter<RepositoryModel, ListRepositoriesAdapter.RepositoryViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding =
            ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RepositoryViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: RepositoryModel) {
            binding.repoName.text = repository.name
            binding.repoDescription.text = repository.description
            binding.repoOwner.text = repository.owner.login

            Glide.with(itemView)
                .load(repository.owner.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.circle_shape)
                .into(binding.avatarImageView)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<RepositoryModel>() {
        override fun areItemsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RepositoryModel,
            newItem: RepositoryModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}
