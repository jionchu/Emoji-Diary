package org.techtest.emoji_diary.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by jionchu on 2021-04-26
 */
@Entity
data class Diary(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "date") val date: String,
        @ColumnInfo(name = "emoji") val emoji: Int,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "content") val content: String,
        @ColumnInfo(name = "like") val like: Boolean
)
