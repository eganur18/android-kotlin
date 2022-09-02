package com.example.helloworld.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DiaryDao {    //supaya bisa insert ke database
    @Insert
    fun insert(diary: Diary)
    @Query("SELECT * FROM diary")              //mendaftar agar bisa ditampilkan
    fun getDiaries() : LiveData<List<Diary>>
}