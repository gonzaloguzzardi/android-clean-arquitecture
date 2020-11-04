package com.learning.app.utils.network

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.annotation.RequiresPermission

class NetworkMonitor
@RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
constructor(private val application: Application) {
    fun startNetworkCallback() {
        val cm: ConnectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()

        cm.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    NetworkState.isNetworkConnected = true
                }

                override fun onLost(network: Network) {
                    NetworkState.isNetworkConnected = false
                }
            })
    }

    fun stopNetworkCallback() {
        val cm: ConnectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        cm.unregisterNetworkCallback(ConnectivityManager.NetworkCallback())
    }
}