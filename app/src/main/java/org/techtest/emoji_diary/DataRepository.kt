package org.techtest.emoji_diary

import org.techtest.emoji_diary.database.AppDatabase
import org.techtest.emoji_diary.database.entity.DiaryEntity
import org.techtest.emoji_diary.database.entity.EmojiEntity

/**
 * Created by jionchu on 2021-04-29
 */
class DataRepository(database: AppDatabase) {

    private var mDatabase: AppDatabase = database

    fun getDiaries(): List<DiaryEntity> {
        return mDatabase.diaryDao().loadAll()
    }

    fun loadDiary(diaryId: Int): DiaryEntity {
        return mDatabase.diaryDao().loadById(diaryId)
    }

    fun loadByLike(): List<DiaryEntity> {
        return mDatabase.diaryDao().loadByLike(true)
    }

    fun loadByEmoji(emojiId: Int): List<DiaryEntity> {
        return mDatabase.diaryDao().loadByEmoji(emojiId)
    }

    fun insertDiary(diary: DiaryEntity) {
        mDatabase.diaryDao().insertDiary(diary)
        mDatabase.emojiDao().increaseCount(diary.emojiId)
    }

    fun deleteDiary(diary: DiaryEntity) {
        mDatabase.diaryDao().deleteDiary(diary)
        mDatabase.emojiDao().decreaseCount(diary.emojiId)
    }

    fun updateDiary(diary: DiaryEntity) {
        mDatabase.diaryDao().updateDiary(diary)
    }

    fun getEmojis(): List<EmojiEntity> {
        return mDatabase.emojiDao().loadAll()
    }

    fun insertEmoji(emoji: EmojiEntity) {
        mDatabase.emojiDao().insert(emoji)
    }

    companion object {
        private var sInstance: DataRepository? = null

        fun getInstance(database: AppDatabase): DataRepository =
            sInstance ?: synchronized(DataRepository::class) {
                sInstance ?: DataRepository(database)
            }
    }
}