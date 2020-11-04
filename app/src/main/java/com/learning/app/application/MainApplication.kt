package com.learning.app.application

import android.app.Application
import androidx.room.Room
import com.learning.app.utils.network.NetworkMonitor
import com.learning.data.room.PostDatabase

class MainApplication : Application() {

    companion object {
        lateinit var database : PostDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext, PostDatabase::class.java, "posts-db").build()
        NetworkMonitor(this).startNetworkCallback()
    }

    override fun onTerminate(){
        super.onTerminate()
        NetworkMonitor(this).stopNetworkCallback()
    }
}