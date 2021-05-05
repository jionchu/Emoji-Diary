package org.techtest.emoji_diary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.techtest.emoji_diary.DataRepository
import org.techtest.emoji_diary.database.Diary
import java.lang.IllegalArgumentException

/**
 * Created by jionchu on 2021-04-30
 */
class DiaryViewModel(private val repository: DataRepository): ViewModel() {

    val allDiaries: LiveData<List<Diary>> = repository.getDiaries()
    val likeDiaries: LiveData<List<Diary>> = repository.loadByLike()
    lateinit var emojiDiaries: LiveData<List<Diary>>

    fun loadByEmoji(emojiId: Int): LiveData<List<Diary>> {
        emojiDiaries = repository.loadByEmoji(emojiId)
        return emojiDiaries
    }

    fun insert(diary: Diary) = viewModelScope.launch {
        repository.insertDiary(diary)
    }

    fun update(diary: Diary) = viewModelScope.launch {
        repository.updateDiary(diary)
    }

}

class DiaryViewModelFactory(private val repository: DataRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiaryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DiaryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}