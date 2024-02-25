package ru.netology.nmedia.dto

import java.math.RoundingMode
import java.text.DecimalFormat

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int = 0,
    val likedByMe: Boolean = false,
    var shares: Int = 0,
    var views: Int = 0,
    var videoId: String? = null
)

fun numberCalculation(number:Int = 0): String {
    val df = DecimalFormat("#.#")
    val dfBig = DecimalFormat("#")
    df.roundingMode = RoundingMode.DOWN
    dfBig.roundingMode = RoundingMode.DOWN
    var remainder = 0.00
    if(number < 1000){
        return number.toString()
    }
    if (number in 1000..9999){
        remainder = number.toDouble()/1000
        return "${df.format(remainder)}K"

    }
    if (number in 10000..999999){
        remainder = number.toDouble()/1000
        return "${dfBig.format(remainder)}K"

    }
    if (number > 999999){
        remainder = number.toDouble()/1000000
        return "${df.format(remainder)}M"
    }
    return "Error"
}
