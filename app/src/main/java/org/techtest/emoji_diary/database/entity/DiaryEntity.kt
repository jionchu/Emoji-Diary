package org.techtest.emoji_diary.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Created by jionchu on 2021-04-26
 */
@Entity(tableName = "diary", foreignKeys = arrayOf(
        ForeignKey(entity = EmojiEntity::class,
                parentColumns = ["id"],
                childColumns =  ["emojiId"]
        )

))
data class DiaryEntity(
        var date: String,
        var emojiId: Int,
        var emojiRes: Int,
        var title: String,
        var content: String,
        var like: Boolean
) {
        @PrimaryKey(autoGenerate = true) var id: Int = 0
}
