package org.techtest.emoji_diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import org.techtest.emoji_diary.database.AppDatabase
import org.techtest.emoji_diary.database.Diary
import org.techtest.emoji_diary.database.Emoji

/**
 * Created by jionchu on 2021-04-29
 */
class DataRepository {

    private var mDatabase: AppDatabase
    private var mObservableDiaries: MediatorLiveData<List<Diary>>
    private var mObservableEmojis: MediatorLiveData<List<Emoji>>

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

    fun getDiaries(): LiveData<List<Diary>> {
        return mObservableDiaries
    }

    fun loadDiary(diaryId: Int): Diary {
        return mDatabase.diaryDao().loadById(diaryId)
    }

    fun loadByLike(): LiveData<List<Diary>> {
        return mDatabase.diaryDao().loadByLike(true)
    }

    fun loadByEmoji(emojiId: Int): LiveData<List<Diary>> {
        return mDatabase.diaryDao().loadByEmoji(emojiId)
    }

    fun insertDiary(diary: Diary) {
        mDatabase.diaryDao().insertDiary(diary)
    }

    fun updateDiary(diary: Diary) {
        mDatabase.diaryDao().updateDiary(diary)
    }

    fun getEmojis(): LiveData<List<Emoji>> {
        return mObservableEmojis
    }

    fun insertEmoji(emoji: Emoji) {
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