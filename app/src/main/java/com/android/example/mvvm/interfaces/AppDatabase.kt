package com.android.example.mvvm.interfaces

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): databaseDAO

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "SEL2DB").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}