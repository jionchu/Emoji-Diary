package org.techtest.emoji_diary.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import org.techtest.emoji_diary.AppExecutors
import org.techtest.emoji_diary.MyApplication.Companion.sInstance
import org.techtest.emoji_diary.R
import java.util.concurrent.Executors

/**
 * Created by jionchu on 2021-04-26
 */
@Database(entities = [Emoji::class, Diary::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun emojiDao(): EmojiDao
    abstract fun diaryDao(): DiaryDao

    companion object {
        private const val DATABASE_NAME = "emoji-diary-db"

        fun getInstance(context: Context, executors: AppExecutors): AppDatabase =
            sInstance ?: synchronized(this) {
                sInstance ?: buildDatabase(context.applicationContext, executors).also { sInstance = it }
            }

        private fun buildDatabase(appContext: Context, executors: AppExecutors): AppDatabase {
            return Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            Log.d("Appdatabase", "onCreate")
                            Executors.newSingleThreadExecutor().execute {
                                val database: AppDatabase = getInstance(appContext, executors)
                                insertEmojiList(database)
                            }
                        }

                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                            Log.d("Appdatabase", "onOpen")
//                            appContext.deleteDatabase(DATABASE_NAME)
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
        }

        fun destroyDatabase() {
            sInstance = null
        }

        fun insertEmojiList(database: AppDatabase) {
            val emojiList: List<Emoji> = listOf(
                Emoji(1, R.drawable.emoji1),
                Emoji(2, R.drawable.emoji2),
                Emoji(3, R.drawable.emoji3),
                Emoji(4, R.drawable.emoji4),
                Emoji(5, R.drawable.emoji5),
                Emoji(6, R.drawable.emoji6),
                Emoji(7, R.drawable.emoji7),
                Emoji(8, R.drawable.emoji8),
                Emoji(9, R.drawable.emoji9),
                Emoji(10, R.drawable.emoji10),
                Emoji(11, R.drawable.emoji11),
                Emoji(12, R.drawable.emoji12),
                Emoji(13, R.drawable.emoji13),
                Emoji(14, R.drawable.emoji14),
                Emoji(15, R.drawable.emoji15),
                Emoji(16, R.drawable.emoji16),
                Emoji(17, R.drawable.emoji17),
                Emoji(18, R.drawable.emoji18),
                Emoji(19, R.drawable.emoji19),
                Emoji(20, R.drawable.emoji20),
                Emoji(21, R.drawable.emoji21),
                Emoji(22, R.drawable.emoji22),
                Emoji(23, R.drawable.emoji23),
                Emoji(24, R.drawable.emoji24),
                Emoji(25, R.drawable.emoji25),
                Emoji(26, R.drawable.emoji26),
                Emoji(27, R.drawable.emoji27),
                Emoji(28, R.drawable.emoji28),
                Emoji(29, R.drawable.emoji29),
                Emoji(30, R.drawable.emoji30),
                Emoji(31, R.drawable.emoji31)
            )

            database.runInTransaction { database.emojiDao().insertAll(emojiList) }
            Log.d("Appdatabase", "insertEmojiList")
        }
    }
}