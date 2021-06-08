package org.techtest.emoji_diary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.techtest.emoji_diary.DataRepository
import org.techtest.emoji_diary.database.entity.DiaryEntity
import java.lang.IllegalArgumentException

/**
 * Created by jionchu on 2021-04-30
 */
class DiaryViewModel(private val repository: DataRepository): ViewModel() {

    val allDiaries: LiveData<List<DiaryEntity>> = repository.getDiaries()
    val likeDiaries: LiveData<List<DiaryEntity>> = repository.loadByLike()
    lateinit var emojiDiaries: LiveData<List<DiaryEntity>>

    fun loadByEmoji(emojiId: Int): LiveData<List<DiaryEntity>> {
        emojiDiaries = repository.loadByEmoji(emojiId)
        return emojiDiaries
    }

    fun insert(diary: DiaryEntity) = viewModelScope.launch {
        repository.insertDiary(diary)
    }

    fun update(diary: DiaryEntity) = viewModelScope.launch {
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