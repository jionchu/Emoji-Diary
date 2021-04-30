package org.techtest.emoji_diary.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.techtest.emoji_diary.MyApplication
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.main.daily.MainDailyFragment
import org.techtest.emoji_diary.main.like.MainLikeFragment
import org.techtest.emoji_diary.main.monthly.MainMonthlyFragment
import org.techtest.emoji_diary.model.Diary
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    private lateinit var mIvDaily: ImageView
    private lateinit var mIvLike: ImageView
    private lateinit var mIvMonthly: ImageView
    private lateinit var mTvDaily: TextView
    private lateinit var mTvLike: TextView
    private lateinit var mTvMonthly: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainDailyFragment())
                .commit()

        mIvDaily = findViewById(R.id.img_tab1)
        mIvLike = findViewById(R.id.img_tab2)
        mIvMonthly = findViewById(R.id.img_tab3)
        mTvDaily = findViewById(R.id.txt_tab1)
        mTvLike = findViewById(R.id.txt_tab2)
        mTvMonthly = findViewById(R.id.txt_tab3)

        val database = MyApplication.sInstance!!
        Log.d("MainActivity", database.emojiDao().getAll().size.toString())
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", MyApplication.sInstance!!.emojiDao().getAll().size.toString())
        Log.d("MainActivity", MyApplication.sInstance!!.diaryDao().getRowCount().toString())
    }

    override fun onStart() {
        super.onStart()

        for (i in 0..30) emojiList[i + 1] = ArrayList()

        for (i in diaryArrayList.indices) {
            if (diaryArrayList[i].favorite) {
                favoriteList.add(i)
            }
            val emoji = diaryArrayList[i].image
            var list = emojiList[emoji]
            if (list == null) list = ArrayList()
            list.add(i)
            emojiList[emoji] = list
        }
    }

    fun customOnClick(view: View) {
        when (view.id) {
            R.id.button_tab1 -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MainDailyFragment())
                        .commit()
                mIvDaily.setImageResource(R.drawable.ic_list)
                mTvDaily.setTextColor(Color.rgb(255, 255, 255))
                mIvLike.setImageResource(R.drawable.ic_favorite_border_unclicked)
                mTvLike.setTextColor(Color.rgb(137, 137, 137))
                mIvMonthly.setImageResource(R.drawable.ic_trending_up_unclicked)
                mTvMonthly.setTextColor(Color.rgb(137, 137, 137))
            }
            R.id.button_tab2 -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MainLikeFragment())
                        .commit()
                mIvDaily.setImageResource(R.drawable.ic_list_unclicked)
                mTvDaily.setTextColor(Color.rgb(137, 137, 137))
                mIvLike.setImageResource(R.drawable.ic_favorite_border)
                mTvLike.setTextColor(Color.rgb(255, 255, 255))
                mIvMonthly.setImageResource(R.drawable.ic_trending_up_unclicked)
                mTvMonthly.setTextColor(Color.rgb(137, 137, 137))
            }
            R.id.button_tab3 -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MainMonthlyFragment())
                        .commit()
                mIvDaily.setImageResource(R.drawable.ic_list_unclicked)
                mTvDaily.setTextColor(Color.rgb(137, 137, 137))
                mIvLike.setImageResource(R.drawable.ic_favorite_border_unclicked)
                mTvLike.setTextColor(Color.rgb(137, 137, 137))
                mIvMonthly.setImageResource(R.drawable.ic_trending_up)
                mTvMonthly.setTextColor(Color.rgb(255, 255, 255))
            }
        }
    }

    companion object {
        val diaryArrayList: ArrayList<Diary> = ArrayList()
        var favoriteList: ArrayList<Int> = ArrayList()
        var emojiList: HashMap<Int, ArrayList<Int>> = HashMap()
    }
}