package ru.netology.nmedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl
private val empty = Post (
    id = 0,
    content = "",
    published = "",
    likes = 0,
    likedByMe = false,
    author = ""
)

class PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)
    fun like(id: Long) = repository.like(id)
    fun share(id: Long) = repository.share(id)
    fun removeById(id: Long) = repository.removeById(id)
    fun save(){
        edited.value?.let {
            repository.save(it)
        }
       edited.value = empty
    }

    fun cancelEdit(){
        edited.value = empty
    }

    fun changeContent(content:String ){
        edited.value?.let {
            val text = content.trim()
            if(it.content == text){
                return
            }
            edited.value = it.copy(content = text)
        }

    }

    fun edit(post: Post) {
        edited.value = post
    }

}
