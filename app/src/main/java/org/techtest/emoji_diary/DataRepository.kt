package org.techtest.emoji_diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import org.techtest.emoji_diary.database.AppDatabase
import org.techtest.emoji_diary.database.entity.DiaryEntity
import org.techtest.emoji_diary.database.entity.EmojiEntity

/**
 * Created by jionchu on 2021-04-29
 */
class DataRepository {

    private var mDatabase: AppDatabase
    private var mObservableDiaries: MediatorLiveData<List<DiaryEntity>>
    private var mObservableEmojis: MediatorLiveData<List<EmojiEntity>>

    constructor(database: AppDatabase) {
        mDatabase = database
        mObservableDiaries = MediatorLiveData()
        mObservableEmojis = MediatorLiveData()

        mObservableDiaries.addSource(mDatabase.diaryDao().loadAll()) {
            diaries ->
            run {
                mObservableDiaries.value = diaries
            }
        }

        mObservableEmojis.addSource(mDatabase.emojiDao().loadAll()) {
            emojis ->
            run {
                mObservableEmojis.value = emojis
            }
        }
    }

    fun getDiaries(): LiveData<List<DiaryEntity>> {
        return mObservableDiaries
    }

    fun loadDiary(diaryId: Int): DiaryEntity {
        return mDatabase.diaryDao().loadById(diaryId)
    }

    fun loadByLike(): LiveData<List<DiaryEntity>> {
        return mDatabase.diaryDao().loadByLike(true)
    }

    fun loadByEmoji(emojiId: Int): LiveData<List<DiaryEntity>> {
        return mDatabase.diaryDao().loadByEmoji(emojiId)
    }

    fun insertDiary(diary: DiaryEntity) {
        mDatabase.diaryDao().insertDiary(diary)
    }

    fun updateDiary(diary: DiaryEntity) {
        mDatabase.diaryDao().updateDiary(diary)
    }

    fun getEmojis(): LiveData<List<EmojiEntity>> {
        return mObservableEmojis
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