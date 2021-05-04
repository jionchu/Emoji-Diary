package org.techtest.emoji_diary.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by jionchu on 2021-04-26
 */
@Dao
interface EmojiDao {
    @Query("SELECT * FROM emoji")
    fun loadAll(): LiveData<List<Emoji>>

    @Query("SELECT * FROM emoji WHERE id in (:emojiId)")
    fun loadByIds(emojiId: IntArray): LiveData<List<Emoji>>

    @Query("SELECT COUNT(id) from emoji")
    fun getRowCount(): Int

    @Insert
    fun insert(emoji: Emoji)

    @Insert
    @JvmSuppressWildcards
    fun insertAll(emojiList: List<Emoji>)
}