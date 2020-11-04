package com.learning.data.room.deletedposts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deleted_posts")
data class DeletedPostEntity(
    @PrimaryKey(autoGenerate = false) val id: String
)