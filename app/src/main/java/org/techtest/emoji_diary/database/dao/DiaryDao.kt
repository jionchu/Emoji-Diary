package org.techtest.emoji_diary.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import org.techtest.emoji_diary.database.entity.DiaryEntity

/**
 * Created by jionchu on 2021-04-26
 */
@Dao
interface DiaryDao {
    @Query("SELECT * FROM diary ORDER BY date DESC")
    fun loadAll(): LiveData<List<DiaryEntity>>

    @Query("SELECT * FROM diary WHERE id = (:diaryId) ORDER BY date DESC")
    fun loadById(diaryId: Int): DiaryEntity

    @Query("SELECT * FROM diary WHERE `like` = (:likeState) ORDER BY date DESC")
    fun loadByLike(likeState: Boolean): LiveData<List<DiaryEntity>>

    @Query("SELECT * FROM diary WHERE emojiId = (:emojiId) ORDER BY date DESC")
    fun loadByEmoji(emojiId: Int): LiveData<List<DiaryEntity>>

    @Query("SELECT COUNT(id) from diary")
    fun getRowCount(): Int

    @Insert
    fun insertDiary(vararg diary: DiaryEntity)

    @Delete
    fun deleteDiary(vararg diary: DiaryEntity)

    @Update
    fun updateDiary(vararg diary: DiaryEntity)
}