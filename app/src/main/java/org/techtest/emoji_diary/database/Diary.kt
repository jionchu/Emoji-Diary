package org.techtest.emoji_diary.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Created by jionchu on 2021-04-26
 */
@Entity(tableName = "diary", foreignKeys = arrayOf(
        ForeignKey(entity = Emoji::class,
                parentColumns = ["id"],
                childColumns =  ["emoji"]
        )
))
data class Diary(
        var date: String,
        var emoji: Int,
        var title: String,
        var content: String,
        var like: Boolean
) {
        @PrimaryKey(autoGenerate = true) var id: Int = 1
}
