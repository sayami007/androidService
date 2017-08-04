package com.a3callistos.service1.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * Created by Bibesh on 8/3/17.
 */

class MyStartedService : Service() {


    companion object {
        val TAG: String = MyStartedService.javaClass.simpleName
    }

    override fun onBind(p0: Intent?): IBinder {
        Log.v(TAG + "BIND", Thread.currentThread().name)
        return null!!
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("HELO")
        var intent: Intent = Intent("action.service.to.activity")
        intent.putExtra("key","value")
        sendBroadcast(intent)
        return START_STICKY
    }
}


