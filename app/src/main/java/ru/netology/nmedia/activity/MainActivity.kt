package ru.netology.nmedia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.numberCalculation
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )

                countLike.text = numberCalculation(post.likes)
                countView.text = numberCalculation(post.views)
                countShare.text = numberCalculation(post.shares)

            }
        }
        binding.like.setOnClickListener {
            viewModel.like()

        }
        binding.share.setOnClickListener {
            viewModel.share()
        }
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
//        with(binding) {
//            author.text = post.author
//            published.text = post.published
//            content.text = post.content
//            if (post.likedByMe) {
//                like.setImageResource(R.drawable.ic_liked_24)
//            }
//            countLike.text = numberCalculation(post.likes)
//            countView.text = numberCalculation(post.views)
//            countShare.text = numberCalculation(post.shares)
//
//            like.setOnClickListener {
//                post.likedByMe = !post.likedByMe
//                like.setImageResource(
//                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
//                )
//                if (post.likedByMe) post.likes++ else post.likes--
//
//                countLike.text = numberCalculation(post.likes)
//            }
//            share.setOnClickListener {
//                post.shares++
//                countShare.text = numberCalculation(post.shares)
//            }
//
//        }
//    }
}


