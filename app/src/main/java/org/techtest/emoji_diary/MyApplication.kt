package org.techtest.emoji_diary

import android.annotation.SuppressLint
import android.app.Application
import org.techtest.emoji_diary.data.DataRepository
import org.techtest.emoji_diary.data.local.AppDatabase
import java.text.SimpleDateFormat

/**
 * Created by jionchu on 2021-04-26
 */
class MyApplication : Application() {

    private lateinit var mAppExecutors: AppExecutors

    override fun onCreate() {
        super.onCreate()
        mAppExecutors = AppExecutors()
        sRepository = getRepository()
    }

    fun getDatabase(): AppDatabase {
        return AppDatabase.getInstance(this, mAppExecutors)
    }

    fun getRepository(): DataRepository {
        return DataRepository.getInstance(getDatabase())
    }

    companion object {
        var sInstance: AppDatabase? = null
        var sRepository: DataRepository? = null

        @SuppressLint("SimpleDateFormat")
        val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        @SuppressLint("SimpleDateFormat")
        val dateStrFormat: SimpleDateFormat = SimpleDateFormat("yyyy년 MM월 dd일")
    }
}