package org.techtest.emoji_diary.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.adapter.EmojiAdapter.EmojiViewHolder
import java.util.*

class EmojiAdapter(context: Context?, emoji_list: Map<Int, ArrayList<Int>?>) : RecyclerView.Adapter<EmojiViewHolder>() {
    private val emojiList: Map<Int, ArrayList<Int>?> = emoji_list
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    class EmojiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivEmoji: ImageView = itemView.findViewById(R.id.emoji_image)
        val tvCount: TextView = itemView.findViewById(R.id.txt_count)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): EmojiViewHolder {
        val view = inflater.inflate(R.layout.emoji_item, null, false)
        return EmojiViewHolder(view)
    }

    override fun onBindViewHolder(diaryViewHolder: EmojiViewHolder, position: Int) {
        val emoji = position + 1
        val count: Int
        count = if (emojiList[emoji] != null) emojiList[emoji]!!.size else 0
        when (emoji) {
            1 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji1)
            2 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji2)
            3 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji3)
            4 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji4)
            5 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji5)
            6 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji6)
            7 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji7)
            8 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji8)
            9 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji9)
            10 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji10)
            11 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji11)
            12 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji12)
            13 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji13)
            14 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji14)
            15 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji15)
            16 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji16)
            17 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji17)
            18 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji18)
            19 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji19)
            20 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji20)
            21 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji21)
            22 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji22)
            23 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji23)
            24 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji24)
            25 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji25)
            26 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji26)
            27 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji27)
            28 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji28)
            29 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji29)
            30 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji30)
            31 -> diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji31)
        }
        diaryViewHolder.tvCount.text = "($count)"
    }

    override fun getItemCount(): Int {
        return emojiList.size
    }

}