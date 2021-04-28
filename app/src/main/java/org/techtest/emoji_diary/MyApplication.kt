package org.techtest.emoji_diary

import android.app.Application
import org.techtest.emoji_diary.database.AppDatabase

/**
 * Created by jionchu on 2021-04-26
 */
class MyApplication : Application() {

    lateinit var mAppExecutors: AppExecutors

    override fun onCreate() {
        super.onCreate()
        mAppExecutors = AppExecutors();

        sInstance = getDatabase()
    }

    fun getDatabase(): AppDatabase? {
        return AppDatabase.getInstance(this, mAppExecutors)
    }

    companion object {
        var sInstance: AppDatabase? = null
    }
}