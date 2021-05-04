package org.techtest.emoji_diary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.database.Emoji

/**
 * Created by jionchu on 2021-05-01
 */
class EmojiDialogAdapter(): ListAdapter<Emoji, EmojiDialogAdapter.EmojiDialogViewHolder>(EmojiDialogComparator()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): EmojiDialogViewHolder {
        return EmojiDialogViewHolder.create(viewGroup)
    }

    override fun onBindViewHolder(viewHolder: EmojiDialogViewHolder, position: Int) {
        val current = getItem(position)
        viewHolder.bind(current)
    }

    class EmojiDialogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val ivEmoji: ImageView = itemView.findViewById(R.id.emoji_dialog_image)

        fun bind(emoji: Emoji) {
            ivEmoji.setImageResource(emoji.image)
        }

        companion object {
            fun create(parent: ViewGroup): EmojiDialogViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.emoji_dialog_item, parent, false)
                return EmojiDialogViewHolder(view)
            }
        }
    }

    class EmojiDialogComparator: DiffUtil.ItemCallback<Emoji>() {
        override fun areItemsTheSame(oldItem: Emoji, newItem: Emoji): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Emoji, newItem: Emoji): Boolean {
            return oldItem.id == newItem.id
        }
    }
}