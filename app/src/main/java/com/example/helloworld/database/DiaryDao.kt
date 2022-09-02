package com.example.helloworld.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DiaryDao {    //supaya bisa insert ke database
    @Insert
    fun insert(diary: Diary)
    @Update
    fun update(diary: Diary)
    @Query("SELECT * FROM diary ORDER BY id DESC")              //mendaftar agar bisa ditampilkan ORDER BY id DESC untuk sorting dr terbaru ke terlama
    fun getDiaries() : LiveData<List<Diary>>
    @Query("SELECT * FROM diary WHERE id = :id")
    fun getDiary(id: Int): LiveData<Diary>
}