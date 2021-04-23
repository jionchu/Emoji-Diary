package org.techtest.emoji_diary.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.adapter.DiaryAdapter.DiaryViewHolder
import org.techtest.emoji_diary.main.MainActivity
import org.techtest.emoji_diary.model.Diary
import java.util.*

class DiaryAdapter(context: Context?, diary_list: ArrayList<Diary>) : RecyclerView.Adapter<DiaryViewHolder>() {
    private val diaryArrayList: ArrayList<Diary>
    private val inflater: LayoutInflater
    private var title: String? = null
    private var content: String? = null
    private var date: Date? = null
    private var emoji = 0

    class DiaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivImage: ImageView
        var ivFavorite: ImageView
        var tvTitle: TextView
        var tvDate: TextView

        init {
            ivImage = itemView.findViewById(R.id.diary_image)
            tvTitle = itemView.findViewById(R.id.diary_title)
            tvDate = itemView.findViewById(R.id.diary_date)
            ivFavorite = itemView.findViewById(R.id.button_heart)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DiaryViewHolder {
        val view = inflater.inflate(R.layout.diary_item, null, false)
        return DiaryViewHolder(view)
    }

    override fun onBindViewHolder(diaryViewHolder: DiaryViewHolder, position: Int) {
        emoji = diaryArrayList[position].image
        title = diaryArrayList[position].title
        date = diaryArrayList[position].date
        content = diaryArrayList[position].content
        when (emoji) {
            1 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji1)
            2 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji2)
            3 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji3)
            4 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji4)
            5 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji5)
            6 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji6)
            7 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji7)
            8 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji8)
            9 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji9)
            10 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji10)
            11 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji11)
            12 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji12)
            13 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji13)
            14 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji14)
            15 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji15)
            16 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji16)
            17 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji17)
            18 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji18)
            19 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji19)
            20 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji20)
            21 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji21)
            22 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji22)
            23 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji23)
            24 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji24)
            25 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji25)
            26 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji26)
            27 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji27)
            28 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji28)
            29 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji29)
            30 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji30)
            31 -> diaryViewHolder.ivImage.setImageResource(R.drawable.emoji31)
        }
        diaryViewHolder.tvTitle.text = title
        diaryViewHolder.tvDate.text = (date!!.getMonth() + 1).toString() + "월 " + date!!.getDate() + "일"
        if (diaryArrayList[position].favorite) {
            diaryViewHolder.ivFavorite.setImageResource(R.drawable.ic_favorite)
        } else diaryViewHolder.ivFavorite.setImageResource(R.drawable.ic_favorite_border_color)
    }

    override fun getItemCount(): Int {
        return diaryArrayList.size
    }

    init {
        inflater = LayoutInflater.from(context)
        diaryArrayList = diary_list
        MainActivity.favoriteList = ArrayList()
        MainActivity.emojiList = HashMap()
        for (i in 0..30) MainActivity.emojiList[i + 1] = ArrayList()
        for (i in diaryArrayList.indices) {
            if (diaryArrayList[i].favorite) {
                MainActivity.favoriteList.add(i)
            }
            emoji = diaryArrayList[i].image
            var list = MainActivity.emojiList[emoji]
            if (list == null) list = ArrayList()
            list.add(i)
            MainActivity.emojiList[emoji] = list
        }
    }
}