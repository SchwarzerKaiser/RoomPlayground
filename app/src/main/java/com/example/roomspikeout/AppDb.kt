package com.example.roomspikeout

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Person::class, Dog::class, Pup::class],
    version = 1
)
abstract class AppDb : RoomDatabase() {
    abstract fun getPersonDao(): PersonDao
}