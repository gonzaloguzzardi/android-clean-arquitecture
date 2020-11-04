package com.learning.data.room.deletedposts

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DeletedPostEntity::class], version = 1, exportSchema = false)
abstract class DeletedPostsDatabase : RoomDatabase() {
    abstract fun deletedPostsDao(): DeletedPostsDao
}