package org.techtest.emoji_diary.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by jionchu on 2021-04-25
 */
@Entity(tableName = "emoji")
data class EmojiEntity(
        @PrimaryKey var id: Int,
        var image: Int
) {
    var count: Int = 0
}
