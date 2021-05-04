package org.techtest.emoji_diary.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by jionchu on 2021-04-26
 */
@Dao
interface DiaryDao {
    @Query("SELECT * FROM diary")
    fun loadAll(): LiveData<List<Diary>>

    @Query("SELECT * FROM diary WHERE id = (:diaryId)")
    fun loadById(diaryId: Int): Diary

    @Query("SELECT * FROM diary WHERE `like` = (:likeState)")
    fun loadByLike(likeState: Boolean): LiveData<List<Diary>>

    @Query("SELECT * FROM diary WHERE emojiId = (:emojiId)")
    fun loadByEmoji(emojiId: Int): LiveData<List<Diary>>

    @Query("SELECT COUNT(id) from diary")
    fun getRowCount(): Int

    @Insert
    fun insertDiary(vararg diary: Diary)

    @Delete
    fun deleteDiary(vararg diary: Diary)

    @Update
    fun updateDiary(vararg diary: Diary)
}