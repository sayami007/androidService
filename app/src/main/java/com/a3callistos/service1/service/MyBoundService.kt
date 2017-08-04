package com.a3callistos.service1.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

/**
 * Created by Bibesh on 8/4/17.
 */

class MyBoundService : Service() {

    val myLocalBinder: MyLocalbinder = MyLocalbinder()


    class MyLocalbinder : Binder() {

        fun getServie(): MyBoundService {
            val a: MyBoundService = MyBoundService()
            return a
        }
    }

    override fun onBind(p0: Intent?): IBinder {
        return myLocalBinder
    }

    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun sub(a: Int, b: Int): Int {
        return a - b
    }

    fun mul(a: Int, b: Int): Int {
        return a * b
    }

    fun div(a: Int, b: Int): Float {
        return a.toFloat() / b.toFloat()
    }
}