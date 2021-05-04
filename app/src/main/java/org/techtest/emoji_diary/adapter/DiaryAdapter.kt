package org.techtest.emoji_diary.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.adapter.DiaryAdapter.DiaryViewHolder
import org.techtest.emoji_diary.database.Diary

class DiaryAdapter : ListAdapter<Diary, DiaryViewHolder>(DiaryComparator()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DiaryViewHolder {
        return DiaryViewHolder.create(viewGroup)
    }

    override fun onBindViewHolder(diaryViewHolder: DiaryViewHolder, position: Int) {
        val current = getItem(position)
        diaryViewHolder.bind(current)
    }

    class DiaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var ivImage: ImageView = itemView.findViewById(R.id.diary_image)
        private var ivLike: ImageView = itemView.findViewById(R.id.button_heart)
        private var tvTitle: TextView = itemView.findViewById(R.id.diary_title)
        private var tvDate: TextView = itemView.findViewById(R.id.diary_date)

        fun bind(diary: Diary) {
            ivImage.setImageResource(diary.emojiRes)
            tvTitle.text = diary.title
            tvDate.text = diary.date

            if (diary.like) ivLike.setImageResource(R.drawable.ic_favorite)
            else ivLike.setImageResource(R.drawable.ic_favorite_border_unclicked)
        }

        companion object {
            fun create(parent: ViewGroup): DiaryViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.diary_item, parent, false)
                return DiaryViewHolder(view)
            }
        }
    }

    class DiaryComparator: DiffUtil.ItemCallback<Diary>() {
        override fun areItemsTheSame(oldItem: Diary, newItem: Diary): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Diary, newItem: Diary): Boolean {
            return oldItem.content == newItem.content
        }
    }
}