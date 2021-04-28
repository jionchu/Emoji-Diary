package org.techtest.emoji_diary.database

import androidx.room.*

/**
 * Created by jionchu on 2021-04-26
 */
@Dao
interface DiaryDao {
    @Query("SELECT * FROM diary")
    fun getAll(): List<Diary>

    @Query("SELECT * FROM diary WHERE id in (:diaryId)")
    fun loadByIds(diaryId: IntArray): Diary

    @Query("SELECT * FROM diary WHERE `like` = (:likeState)")
    fun loadByLike(likeState: Boolean): List<Diary>

    @Insert
    fun insertDiary(vararg diary: Diary)

    @Delete
    fun deleteDiary(vararg diary: Diary)

    @Update
    fun updateDiary(vararg diary: Diary)
}