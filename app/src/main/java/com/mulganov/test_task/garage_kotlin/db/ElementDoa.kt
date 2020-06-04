package com.mulganov.test_task.garage_kotlin.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ElementDoa {
    // Добавление Element в бд
    @Insert
    fun insertAll(vararg elements: Element?)

    // Удаление Element из бд
    @Delete
    fun delete(vararg elements: Element?)

    // Получение всех Element из бд
    @get:Query("SELECT * FROM element")
    val allElement: List<Element?>?
}