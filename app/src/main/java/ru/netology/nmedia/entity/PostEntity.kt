package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean,
    val likes: Int = 0,
    var shares: Int = 0,
    var views: Int = 0,
    var videoId: String? = null
) {
    fun toDto() = Post(id, author = "Me", content, published = "now", likes, likedByMe, shares, views, videoId = "dQw4w9WgXcQ")

    companion object {
        fun fromDto(dto: Post) =
            PostEntity(
                dto.id,
                dto.author,
                dto.content,
                dto.published,
                dto.likedByMe,
                dto.likes,
                dto.shares,
                dto.views,
                dto.videoId
            )

    }
}