package org.techtest.emoji_diary.model

import java.util.*

data class Diary(var emoji: Int, var title: String, var date: Date, var content: String, var favorite: Boolean) {
    var image = emoji
}