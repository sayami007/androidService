package com.a3callistos.service1.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Messenger

/**
 * Created by Bibesh on 8/4/17.
 */
class MyMessengerService : Service() {



    override fun onBind(p0: Intent?): IBinder {
    return null
    }

    fun add(a: Int, b: Int): Int {
        return a + b
    }
}