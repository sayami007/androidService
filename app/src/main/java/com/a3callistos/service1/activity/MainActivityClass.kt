package com.a3callistos.service1.activity

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.support.v7.app.AppCompatActivity
import android.util.Log

import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.a3callistos.service1.R
import com.a3callistos.service1.service.MyIntentService
import com.a3callistos.service1.service.MyStartedService
import com.a3callistos.service1.activity.MainActivityClass


/**
 * Created by Bibesh on 8/3/17.
 */
var tvIntentService: TextView? = null
var handler: Handler? = null
var myContext: Context? = null

class MainActivityClass : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvIntentService = findViewById(R.id.tv_intent_service) as TextView
        handler = Handler()
        myContext = applicationContext
    }


    fun startService(v: View) {
        var intent: Intent = Intent(applicationContext, MyStartedService::class.java)
        intent.putExtra("timer", 10000)
        startService(intent)
    }

    fun stopService(v: View) {
        var intent: Intent = Intent(baseContext, MyStartedService::class.java)
        stopService(intent)
    }


    fun startIntentService(v: View) {
        var myResultReceiver: MyResultReceiver = MyResultReceiver(null, applicationContext)
        tvIntentService?.text = "HELO"
        var intentService: Intent = Intent(baseContext, MyIntentService::class.java)
        intentService.putExtra("receiver", myResultReceiver)
        startService(intentService)
    }

    override fun onResume() {
        super.onResume()
        var intentFilter: IntentFilter = IntentFilter()
        intentFilter.addAction("action.service.to.activity")
        registerReceiver(receiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    var receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            println(intent.getStringExtra("key"))
        }
    }


    fun secondActivity(view: View) {
        startActivity(Intent(this, SecondActivity::class.java))
    }

    class MyResultReceiver : ResultReceiver {


        constructor(handler: Handler?, context: Context) : super(handler)

        override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
            super.onReceiveResult(resultCode, resultData)
            println("CODE 1")
            if (resultCode == 10 && resultData != null) {
                handler?.post {
                    Toast.makeText(myContext, "HELLO", Toast.LENGTH_LONG).show()
                    var intent: Intent = Intent(myContext, MainActivityClass::class.java)
                    myContext?.startActivity(intent)
                    tvIntentService?.text = "HEi"
                    println(Thread.currentThread().name)
                }
            }
        }
    }
}