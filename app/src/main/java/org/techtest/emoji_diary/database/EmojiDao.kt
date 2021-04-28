package org.techtest.emoji_diary.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by jionchu on 2021-04-26
 */
@Dao
interface EmojiDao {
    @Query("SELECT * FROM emoji")
    fun getAll(): List<Emoji>

    @Query("SELECT * FROM emoji WHERE id in (:emojiId)")
    fun loadByIds(emojiId: IntArray): List<Emoji>

    @Insert
    fun insertEmoji(vararg emoji: Emoji)
}