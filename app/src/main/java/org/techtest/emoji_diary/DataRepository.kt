package org.techtest.emoji_diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import org.techtest.emoji_diary.database.AppDatabase
import org.techtest.emoji_diary.database.Diary

/**
 * Created by jionchu on 2021-04-29
 */
class DataRepository {

    private var mDatabase: AppDatabase
    private var mObservableDiaries: MediatorLiveData<List<Diary>>

    constructor(database: AppDatabase) {
        mDatabase = database
        mObservableDiaries = MediatorLiveData()

        mObservableDiaries.addSource(mDatabase.diaryDao().loadAll()) {
            diaries ->
            run {
                mObservableDiaries.value = diaries
            }
        }
    }

    fun getDiaries(): LiveData<List<Diary>> {
        return mObservableDiaries
    }

    fun loadDiary(diaryId: Int): LiveData<Diary> {
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

    companion object {
        private var sInstance: DataRepository? = null

        fun getInstance(database: AppDatabase): DataRepository =
            sInstance ?: synchronized(DataRepository::class) {
                sInstance ?: DataRepository(database)
            }
    }
}