package org.techtest.emoji_diary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import org.techtest.emoji_diary.AppExecutors
import org.techtest.emoji_diary.MyApplication.Companion.sInstance

/**
 * Created by jionchu on 2021-04-26
 */
@Database(entities = [Emoji::class, Diary::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun emojiDao(): EmojiDao
    abstract fun diaryDao(): DiaryDao

    companion object {
        const val DATABASE_NAME = "emoji-diary-db"

        fun getInstance(context: Context, executors: AppExecutors): AppDatabase? {
            if (sInstance == null) {
                synchronized(AppDatabase::class) {
                    if (sInstance == null) {
                        sInstance = buildDatabase(context.applicationContext, executors);
                    }
                }
            }
            return sInstance;
        }

        fun buildDatabase(appContext: Context, executors: AppExecutors): AppDatabase {
            return Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            /*executors.diskIO().execute(() -> {
                                var database: AppDatabase = getInstance(appContext, executors)
                                database.setDatabaseCreated();
                            })*/
                        }
                    }).build()
        }
    }
}