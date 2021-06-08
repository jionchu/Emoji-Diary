package org.techtest.emoji_diary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.techtest.emoji_diary.DataRepository
import org.techtest.emoji_diary.database.entity.EmojiEntity
import java.lang.IllegalArgumentException

/**
 * Created by jionchu on 2021-05-01
 */
class EmojiViewModel(private val repository: DataRepository): ViewModel() {

    val allEmojis: LiveData<List<EmojiEntity>> = repository.getEmojis()

    fun insert(emoji: EmojiEntity) = viewModelScope.launch {
        repository.insertEmoji(emoji)
    }
}

class EmojiViewModelFactory(private val repository: DataRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmojiViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmojiViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}