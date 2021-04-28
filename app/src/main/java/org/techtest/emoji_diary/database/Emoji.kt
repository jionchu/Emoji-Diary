package org.techtest.emoji_diary.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by jionchu on 2021-04-25
 */
@Entity
data class Emoji(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "image") val image: Int
)
