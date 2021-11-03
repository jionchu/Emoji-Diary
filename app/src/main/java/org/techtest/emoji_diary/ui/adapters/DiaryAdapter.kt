package org.techtest.emoji_diary.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import org.techtest.emoji_diary.ui.adapters.DiaryAdapter.DiaryViewHolder
import org.techtest.emoji_diary.data.local.entity.DiaryEntity
import org.techtest.emoji_diary.databinding.ItemDiaryBinding

class DiaryAdapter : ListAdapter<DiaryEntity, DiaryViewHolder>(DiaryComparator()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DiaryViewHolder {
        return DiaryViewHolder.create(viewGroup)
    }

    override fun onBindViewHolder(diaryViewHolder: DiaryViewHolder, position: Int) {
        diaryViewHolder.bind(getItem(position))
    }

    class DiaryViewHolder(private val binding: ItemDiaryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(diary: DiaryEntity) {
            binding.diary = diary
        }

        companion object {
            fun create(parent: ViewGroup): DiaryViewHolder {
                return DiaryViewHolder(
                    ItemDiaryBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    class DiaryComparator : DiffUtil.ItemCallback<DiaryEntity>() {
        override fun areItemsTheSame(oldItem: DiaryEntity, newItem: DiaryEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DiaryEntity, newItem: DiaryEntity): Boolean {
            return oldItem.content == newItem.content
        }
    }
}