package com.learning.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PostDataEntity::class], version = 1, exportSchema = false)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
}