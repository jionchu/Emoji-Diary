package org.techtest.emoji_diary.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.database.entity.EmojiEntity

/**
 * Created by jionchu on 2021-05-01
 */
class EmojiDialogAdapter(): ListAdapter<EmojiEntity, EmojiDialogAdapter.EmojiDialogViewHolder>(EmojiDialogComparator()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): EmojiDialogViewHolder {
        return EmojiDialogViewHolder.create(viewGroup)
    }

    override fun onBindViewHolder(viewHolder: EmojiDialogViewHolder, position: Int) {
        val current = getItem(position)
        viewHolder.bind(current)
    }

    class EmojiDialogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val ivEmoji: ImageView = itemView.findViewById(R.id.emoji_dialog_image)

        fun bind(emoji: EmojiEntity) {
            ivEmoji.setImageResource(emoji.image)
        }

        companion object {
            fun create(parent: ViewGroup): EmojiDialogViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_dialog_emoji, parent, false)
                return EmojiDialogViewHolder(view)
            }
        }
    }

    class EmojiDialogComparator: DiffUtil.ItemCallback<EmojiEntity>() {
        override fun areItemsTheSame(oldItem: EmojiEntity, newItem: EmojiEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: EmojiEntity, newItem: EmojiEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }
}