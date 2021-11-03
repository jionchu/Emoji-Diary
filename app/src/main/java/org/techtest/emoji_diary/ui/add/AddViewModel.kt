package org.techtest.emoji_diary.ui.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.techtest.emoji_diary.data.DataRepository
import org.techtest.emoji_diary.data.local.entity.EmojiEntity
import java.lang.IllegalArgumentException

class AddViewModel(private val repository: DataRepository) : ViewModel() {

    val allEmojis: MutableLiveData<List<EmojiEntity>> = MutableLiveData(repository.getEmojis())

    fun insert(emoji: EmojiEntity) = viewModelScope.launch {
        repository.insertEmoji(emoji)
    }
}

class AddViewModelFactory(private val repository: DataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}