package com.learning.app.application

import android.app.Application
import androidx.room.Room
import com.learning.app.utils.network.NetworkMonitor
import com.learning.data.room.deletedposts.DeletedPostsDatabase
import com.learning.data.room.posts.PostDatabase

class MainApplication : Application() {

    companion object {
        lateinit var postsDatabase: PostDatabase
        lateinit var deletedPostsDatabase: DeletedPostsDatabase
    }

    override fun onCreate() {
        super.onCreate()
        postsDatabase =
            Room.databaseBuilder(applicationContext, PostDatabase::class.java, "posts-db").build()
        deletedPostsDatabase = Room.databaseBuilder(
            applicationContext,
            DeletedPostsDatabase::class.java,
            "deleted-posts-db"
        ).build()
        NetworkMonitor(this).startNetworkCallback()
    }

    override fun onTerminate() {
        super.onTerminate()
        NetworkMonitor(this).stopNetworkCallback()
    }
}