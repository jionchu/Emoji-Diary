package org.techtest.emoji_diary.ui.main

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.ui.like.LikeFragment
import org.techtest.emoji_diary.ui.monthly.MonthlyFragment

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
            .replace(R.id.main_fl_container, DailyFragment())
            .commit()

        mIvDaily = findViewById(R.id.main_iv_daily)
        mIvLike = findViewById(R.id.main_iv_favorite)
        mIvMonthly = findViewById(R.id.main_iv_monthly)
        mTvDaily = findViewById(R.id.main_tv_daily)
        mTvLike = findViewById(R.id.main_tv_favorite)
        mTvMonthly = findViewById(R.id.main_tv_monthly)
    }

    fun customOnClick(view: View) {
        when (view.id) {
            R.id.main_cl_daily -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fl_container, DailyFragment())
                    .commit()
                mIvDaily.setImageResource(R.drawable.ic_list)
                mTvDaily.setTextColor(Color.rgb(255, 255, 255))
                mIvLike.setImageResource(R.drawable.ic_favorite_border_unclicked)
                mTvLike.setTextColor(Color.rgb(137, 137, 137))
                mIvMonthly.setImageResource(R.drawable.ic_trending_up_unclicked)
                mTvMonthly.setTextColor(Color.rgb(137, 137, 137))
            }
            R.id.main_cl_favorite -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fl_container, LikeFragment())
                    .commit()
                mIvDaily.setImageResource(R.drawable.ic_list_unclicked)
                mTvDaily.setTextColor(Color.rgb(137, 137, 137))
                mIvLike.setImageResource(R.drawable.ic_favorite_border)
                mTvLike.setTextColor(Color.rgb(255, 255, 255))
                mIvMonthly.setImageResource(R.drawable.ic_trending_up_unclicked)
                mTvMonthly.setTextColor(Color.rgb(137, 137, 137))
            }
            R.id.main_cl_monthly -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fl_container, MonthlyFragment())
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
}