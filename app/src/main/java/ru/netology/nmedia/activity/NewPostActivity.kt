package ru.netology.nmedia.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val postContent = intent.getStringExtra("postContent")
        if (postContent != null) {
            binding.edit.setText(postContent)
        }
        binding.edit.requestFocus()
        binding.ok.setOnClickListener {
            val intent = Intent()
            if (binding.edit.text.isNullOrBlank()) {
                setResult(RESULT_CANCELED, intent)
            } else {
                val content = binding.edit.text.toString()
                intent.putExtra("postContent", content)
                setResult(RESULT_OK, intent)
            }
            finish()
        }
    }
}