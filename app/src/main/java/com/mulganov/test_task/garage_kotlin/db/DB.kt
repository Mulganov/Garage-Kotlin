package com.mulganov.test_task.garage_kotlin.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Element::class /*, AnotherEntityType.class, AThirdEntityType.class */],
    version = 1,
    exportSchema = false
)
abstract class DB : RoomDatabase() {
    abstract val elementDoa: ElementDoa?
}