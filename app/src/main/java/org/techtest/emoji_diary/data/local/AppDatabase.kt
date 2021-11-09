package org.techtest.emoji_diary.data.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import org.techtest.emoji_diary.MyApplication.Companion.sInstance
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.data.local.dao.DiaryDao
import org.techtest.emoji_diary.data.local.dao.EmojiDao
import org.techtest.emoji_diary.data.local.entity.DiaryEntity
import org.techtest.emoji_diary.data.local.entity.EmojiEntity
import java.util.concurrent.Executors

/**
 * Created by jionchu on 2021-04-26
 */
@Database(entities = [EmojiEntity::class, DiaryEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun emojiDao(): EmojiDao
    abstract fun diaryDao(): DiaryDao

    companion object {
        private const val DATABASE_NAME = "emoji-diary-db"

        fun getInstance(context: Context): AppDatabase =
            sInstance ?: synchronized(this) {
                sInstance ?: buildDatabase(context.applicationContext).also { sInstance = it }
            }

        private fun buildDatabase(appContext: Context): AppDatabase {
            return Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            Log.d("Appdatabase", "onCreate")
                            Executors.newSingleThreadExecutor().execute {
                                val database: AppDatabase = getInstance(appContext)
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
            val emojiList: List<EmojiEntity> = listOf(
                EmojiEntity(1, R.drawable.emoji1),
                EmojiEntity(2, R.drawable.emoji2),
                EmojiEntity(3, R.drawable.emoji3),
                EmojiEntity(4, R.drawable.emoji4),
                EmojiEntity(5, R.drawable.emoji5),
                EmojiEntity(6, R.drawable.emoji6),
                EmojiEntity(7, R.drawable.emoji7),
                EmojiEntity(8, R.drawable.emoji8),
                EmojiEntity(9, R.drawable.emoji9),
                EmojiEntity(10, R.drawable.emoji10),
                EmojiEntity(11, R.drawable.emoji11),
                EmojiEntity(12, R.drawable.emoji12),
                EmojiEntity(13, R.drawable.emoji13),
                EmojiEntity(14, R.drawable.emoji14),
                EmojiEntity(15, R.drawable.emoji15),
                EmojiEntity(16, R.drawable.emoji16),
                EmojiEntity(17, R.drawable.emoji17),
                EmojiEntity(18, R.drawable.emoji18),
                EmojiEntity(19, R.drawable.emoji19),
                EmojiEntity(20, R.drawable.emoji20),
                EmojiEntity(21, R.drawable.emoji21),
                EmojiEntity(22, R.drawable.emoji22),
                EmojiEntity(23, R.drawable.emoji23),
                EmojiEntity(24, R.drawable.emoji24),
                EmojiEntity(25, R.drawable.emoji25),
                EmojiEntity(26, R.drawable.emoji26),
                EmojiEntity(27, R.drawable.emoji27),
                EmojiEntity(28, R.drawable.emoji28),
                EmojiEntity(29, R.drawable.emoji29),
                EmojiEntity(30, R.drawable.emoji30),
                EmojiEntity(31, R.drawable.emoji31)
            )

            database.runInTransaction { database.emojiDao().insertAll(emojiList) }
            Log.d("Appdatabase", "insertEmojiList")
        }
    }
}