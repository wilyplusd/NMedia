package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.NewPostFragment.Companion.textArg
import ru.netology.nmedia.databinding.FragmentViewPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.StringArg
import ru.netology.nmedia.viewmodel.PostViewModel


class ViewPost : Fragment() {


    companion object {
        var Bundle.idPost: String? by StringArg
        var Bundle.author: String? by StringArg
        var Bundle.content: String? by StringArg
        var Bundle.published: String? by StringArg
        var Bundle.videoId: String? by StringArg
        var Bundle.likes: String? by StringArg
    }

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentViewPostBinding.inflate(
            inflater,
            container,
            false
        )

        arguments?.author
            ?.let(binding.author::setText)
        arguments?.content
            ?.let(binding.content::setText)
        arguments?.published
            ?.let(binding.published::setText)

        Picasso.get().load("https://img.youtube.com/vi/${arguments?.videoId}/0.jpg")
            .into(binding.video)

        val post: Post = Post(
            arguments?.idPost?.toLong() ?: 0,
            arguments?.author.toString(),
            arguments?.content.toString(),
            arguments?.published.toString(),
            arguments?.likes?.toInt() ?: 0
        )


        binding.menu.setOnClickListener {
            PopupMenu(it.context, it).apply {
                inflate(R.menu.options_post)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.remove -> {
                            viewModel.removeById(post.id)
                            findNavController().navigate(
                                R.id.action_viewPost_to_feedFragment
                            )
                            true

                        }

                        R.id.edit -> {
                            viewModel.edit(post)
                            findNavController().navigate(
                                R.id.action_viewPost_to_newPostFragment,
                                Bundle().apply {
                                    textArg = post.content
                                }
                            )
                            true
                        }

                        else -> false
                    }
                }
            }.show()
        }

        return binding.root
    }
}

