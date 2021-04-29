package org.techtest.emoji_diary.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by jionchu on 2021-04-25
 */
@Entity(tableName = "emoji")
data class Emoji(
        @PrimaryKey var id: Int,
        var image: Int
)
