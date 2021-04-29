package org.techtest.emoji_diary

import android.app.Application
import org.techtest.emoji_diary.database.AppDatabase

/**
 * Created by jionchu on 2021-04-26
 */
class MyApplication : Application() {

    private lateinit var mAppExecutors: AppExecutors

    override fun onCreate() {
        super.onCreate()
        mAppExecutors = AppExecutors()
        getRepository()
    }

    fun getDatabase(): AppDatabase {
        return AppDatabase.getInstance(this, mAppExecutors)
    }

    fun getRepository(): DataRepository {
        return DataRepository.getInstance(getDatabase())
    }

    companion object {
        var sInstance: AppDatabase? = null
    }
}