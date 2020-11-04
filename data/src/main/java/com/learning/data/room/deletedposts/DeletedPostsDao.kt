package com.learning.data.room.deletedposts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DeletedPostsDao {

    @Query("SELECT * FROM deleted_posts")
    suspend fun getAll(): List<DeletedPostEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDeletedPost(post: DeletedPostEntity)
}