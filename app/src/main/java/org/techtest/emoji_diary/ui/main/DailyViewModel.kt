package org.techtest.emoji_diary.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.techtest.emoji_diary.data.DataRepository
import org.techtest.emoji_diary.data.local.entity.DiaryEntity
import java.lang.IllegalArgumentException

class DailyViewModel(private val repository: DataRepository) : ViewModel() {
    private val _isEmpty: MutableLiveData<Boolean> = MutableLiveData()
    val isEmpty: LiveData<Boolean> get() = _isEmpty
    private val _diaries: MutableLiveData<List<DiaryEntity>> = MutableLiveData()
    val diaries: LiveData<List<DiaryEntity>> get() = _diaries

    fun loadAllDiaries() {
        _diaries.value = repository.getDiaries()
        _isEmpty.value = diaries.value?.isEmpty()
    }

    fun updateDiary(diary: DiaryEntity) = repository.updateDiary(diary)

    fun deleteDiary(diary: DiaryEntity) = repository.deleteDiary(diary)
}

@Suppress("UNCHECKED_CAST")
class DailyViewModelFactory(private val repository: DataRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DailyViewModel::class.java))
            return DailyViewModel(repository) as T
        else throw IllegalArgumentException("Unknown ViewModel class")
    }
}