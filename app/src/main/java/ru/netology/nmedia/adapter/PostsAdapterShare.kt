//package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.numberCalculation

typealias OnListenerShare = (post: Post) -> Unit

class PostsAdapterShare(private val onListener: OnListenerShare) : RecyclerView.Adapter<PostViewHolderShare>() {
    var list = emptyList<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolderShare {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolderShare(binding, onListener)
    }

    override fun onBindViewHolder(holder: PostViewHolderShare, position: Int) {
        val post = list[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = list.size
}

class PostViewHolderShare(
    private val binding: CardPostBinding,
    private val onListener: OnListenerShare
): RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post){
        binding.apply{
            author.text = post.author
            published.text = post.published
            content.text = post.content
            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
            )

            countLike.text = numberCalculation(post.likes)
            countView.text = numberCalculation(post.views)
            countShare.text = numberCalculation(post.shares)
            share.setOnClickListener{
                onListener(post)
            }
        }
    }

}