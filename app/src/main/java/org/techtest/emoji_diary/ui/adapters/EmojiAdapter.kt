package org.techtest.emoji_diary.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.ui.adapters.EmojiAdapter.EmojiViewHolder
import org.techtest.emoji_diary.database.entity.EmojiEntity

class EmojiAdapter() : ListAdapter<EmojiEntity, EmojiViewHolder>(EmojiComparator()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): EmojiViewHolder {
        return EmojiViewHolder.create(viewGroup)
    }

    override fun onBindViewHolder(emojiViewHolder: EmojiViewHolder, position: Int) {
        val current = getItem(position)
        emojiViewHolder.bind(current)
    }

    class EmojiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivEmoji: ImageView = itemView.findViewById(R.id.emoji_image)
        private val tvCount: TextView = itemView.findViewById(R.id.txt_count)

        fun bind(emoji: EmojiEntity) {
            ivEmoji.setImageResource(emoji.image)
            val countText = "(${emoji.count})"
            tvCount.text = countText
        }

        companion object {
            fun create(parent: ViewGroup): EmojiViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.emoji_item, parent, false)
                return EmojiViewHolder(view)
            }
        }
    }

    class EmojiComparator: DiffUtil.ItemCallback<EmojiEntity>() {
        override fun areItemsTheSame(oldItem: EmojiEntity, newItem: EmojiEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: EmojiEntity, newItem: EmojiEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }

}