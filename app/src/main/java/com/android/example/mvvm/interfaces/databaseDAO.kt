@file:Suppress("AndroidUnresolvedRoomSqlReference")

package com.android.example.mvvm.interfaces

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.android.example.mvvm.domain.model.Color


@Dao
interface databaseDAO {
    @Query("SELECT * FROM Color")
    fun getALlOrders(): List<Color?>?

    @Insert
    fun insertOrder(users: List<Color>?)
}