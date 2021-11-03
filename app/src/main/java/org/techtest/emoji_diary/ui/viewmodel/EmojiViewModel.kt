package org.techtest.emoji_diary.ui.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.techtest.emoji_diary.data.DataRepository
import org.techtest.emoji_diary.data.local.entity.EmojiEntity
import java.lang.IllegalArgumentException

/**
 * Created by jionchu on 2021-05-01
 */
class EmojiViewModel(private val repository: DataRepository) : ViewModel() {

    val allEmojis: MutableLiveData<List<EmojiEntity>> = MutableLiveData(repository.getEmojis())

    fun insert(emoji: EmojiEntity) = viewModelScope.launch {
        repository.insertEmoji(emoji)
    }
}

class EmojiViewModelFactory(private val repository: DataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmojiViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmojiViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}