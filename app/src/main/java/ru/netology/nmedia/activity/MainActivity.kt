package ru.netology.nmedia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.Toast
import androidx.activity.result.launch
import androidx.activity.viewModels

import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter

import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel

//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val viewModel: PostViewModel by viewModels()
//
//        val adapter = PostsAdapter(object : OnInteractionListener {
//            override fun onEdit(post: Post) {
//                viewModel.edit(post)
//            }
//
//            override fun onLike(post: Post) {
//                viewModel.like(post.id)
//            }
//
//            override fun onShare(post: Post) {
//                viewModel.share(post.id)
//            }
//
//            override fun onRemove(post: Post) {
//                viewModel.removeById(post.id)
//            }
//        })
//
//        binding.list.adapter = adapter
//        viewModel.data.observe(this) { posts ->
//            adapter.submitList(posts)
//        }
//        viewModel.edited.observe(this) { post ->
//            if (post.id == 0L) {
//                return@observe
//            }
//
//            binding.groupEditView.visibility = View.VISIBLE
//            binding.contentPreviewLabel.setText(post.content)
//
//            with(binding.content) {
//                requestFocus()
//                setText(post.content)
//
//            }
//
//
//        }
//        binding.cancelButton.setOnClickListener {
//            binding.groupEditView.visibility = View.GONE
//            with(binding.content) {
//                setText("")
//                clearFocus()
//                AndroidUtils.hideKeyboard(this)
//                viewModel.cancelEdit()
//
//            }
//
//        }
//        val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
//            result ?: return@registerForActivityResult
//            viewModel.changeContent(result)
//            viewModel.save()
//        }
//
//        binding.fab.setOnClickListener {
//            newPostLauncher.launch()
//        }
////        binding.saveButton.setOnClickListener {
////            with(binding.content) {
////                if (text.isNullOrBlank()) {
////                    Toast.makeText(
////                        this@MainActivity,
////                        context.getString(R.string.error_empty_content),
////                        Toast.LENGTH_SHORT
////                    ).show()
////                    return@setOnClickListener
////                }
////
////                viewModel.changeContent(text.toString())
////                viewModel.save()
////
////                setText("")
////                clearFocus()
////                AndroidUtils.hideKeyboard(this)
////                binding.groupEditView.visibility = View.GONE
////            }
////        }
//    }
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        val newPostLauncher= registerForActivityResult(NewPostResultContract()) { result ->
            if (result != null) {
                viewModel.changeContent(result)
                viewModel.save()
            }
        }

        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onEdit(post: Post) {
                viewModel.edit(post)
                newPostLauncher.launch(post)
            }

            override fun onLike(post: Post) {
                viewModel.like(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onShare(post: Post) {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }

                val shareIntent =
                    Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
            }
        })

        binding.list.adapter = adapter

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        binding.fab.setOnClickListener {
            viewModel.cancelEdit()
            newPostLauncher.launch(null)
        }
    }
}








