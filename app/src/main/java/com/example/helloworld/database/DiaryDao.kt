package com.example.helloworld.database

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface DiaryDao {    //supaya bisa insert ke database
    @Insert
    fun insert(diary: Diary)
}