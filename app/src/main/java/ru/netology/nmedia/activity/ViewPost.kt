package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentViewPostBinding
import ru.netology.nmedia.util.StringArg

class ViewPost : Fragment() {
    companion object {
        var Bundle.author: String? by StringArg
        var Bundle.content: String? by StringArg
        var Bundle.published: String? by StringArg
        var Bundle.videoId: String? by StringArg
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        Picasso.get().load("https://img.youtube.com/vi/${arguments?.videoId}/0.jpg").into(binding.video)

        binding.menu.setOnClickListener {
            PopupMenu(it.context, it).apply {
                inflate(R.menu.options_post)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.remove -> {
                            //onInteractionListener.onRemove(post)
                            true
                        }

                        R.id.edit -> {
                            //onInteractionListener.onEdit(post)
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