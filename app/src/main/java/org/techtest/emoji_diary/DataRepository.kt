package org.techtest.emoji_diary

import org.techtest.emoji_diary.database.AppDatabase

/**
 * Created by jionchu on 2021-04-29
 */
class DataRepository {

    private lateinit var mDatabase: AppDatabase

    constructor(database: AppDatabase) {
        mDatabase = database
    }

    companion object {
        private var sInstance: DataRepository? = null

        fun getInstance(database: AppDatabase): DataRepository =
            sInstance ?: synchronized(DataRepository::class) {
                sInstance ?: DataRepository(database)
            }
    }
}