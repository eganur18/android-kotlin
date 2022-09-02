package com.example.helloworld.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Diary::class], version = 1)
abstract class DiaryDb : RoomDatabase() {     //kenapa abstract, karena implementasinya oleh Room
    abstract val dao: DiaryDao
    companion object {      //object yg hanya ada satu

        @Volatile
        private var INSTANCE: DiaryDb? = null
        fun getInstance(context: Context): DiaryDb {
            //bisa saja ada di thread lain maka lakukan sync
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DiaryDb::class.java,
                        "diary.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}