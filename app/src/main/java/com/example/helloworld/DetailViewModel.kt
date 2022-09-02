package com.example.helloworld

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.helloworld.database.Diary
import com.example.helloworld.database.DiaryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class DetailViewModel(private val db: DiaryDao) : ViewModel() {
                                  //untuk memanggil diary dr database
    fun insertDiary(diary: Diary){
        viewModelScope.launch(Dispatchers.IO) {
            db.insert(diary)
        }
    }
    fun updateDiary(diary: Diary){
        viewModelScope.launch(Dispatchers.IO) {
            db.update(diary)
        }
    }
    fun getDiary(id: Int) = db.getDiary(id)
}

class DetailViewModelFactory(private val db: DiaryDao) : ViewModelProvider.Factory {  //membuat factory ketika di viewmodel ada parameter
    @Suppress("unchecked_cast") //supaya "as" tidak warning
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(db) as T
        }
        throw IllegalArgumentException("ViewModel tidak ada")
    }
}