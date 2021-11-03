package org.techtest.emoji_diary.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.techtest.emoji_diary.data.local.entity.EmojiEntity

/**
 * Created by jionchu on 2021-04-26
 */
@Dao
interface EmojiDao {
    @Query("SELECT * FROM emoji")
    fun loadAll(): List<EmojiEntity>

    @Query("SELECT * FROM emoji WHERE id in (:emojiId)")
    fun loadByIds(emojiId: IntArray): List<EmojiEntity>

    @Query("SELECT COUNT(id) from emoji")
    fun getRowCount(): Int

    @Insert
    fun insert(emoji: EmojiEntity)

    @Insert
    @JvmSuppressWildcards
    fun insertAll(emojiList: List<EmojiEntity>)

    @Query("UPDATE emoji SET count = count+1 WHERE id = :id")
    fun increaseCount(id: Int)

    @Query("UPDATE emoji SET count = count-1 WHERE id = :id")
    fun decreaseCount(id: Int)
}