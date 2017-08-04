package com.a3callistos.service1.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import com.a3callistos.service1.R
import com.a3callistos.service1.service.MyBoundService

/**
 * Created by Bibesh on 8/4/17.
 */
class SecondActivity : AppCompatActivity() {

    var isBound: Boolean = false
    var myBoundService: MyBoundService? = null

    var myServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound = false

        }

        override fun onServiceConnected(p0: ComponentName?, iBinder: IBinder?) {
            var myLocalBinder: MyBoundService.MyLocalbinder = iBinder as MyBoundService.MyLocalbinder
            myBoundService = myLocalBinder.getServie()
            isBound = true
        }

    }

    override fun onStart() {
        super.onStart()
        var intent: Intent = Intent(this, MyBoundService::class.java)
        bindService(intent, myServiceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(myServiceConnection)
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    fun onCreateEvent(v: View) {
        val etNumOne: EditText = findViewById(R.id.etNumOne) as EditText
        val etNumTwo: EditText = findViewById(R.id.etNumTwo) as EditText
        val txvResult: TextView = findViewById(R.id.txvResult) as TextView

        var num1: Int = etNumOne.text.toString().toInt()
        var num2: Int = etNumTwo.text.toString().toInt()
        var resultStr: String? = null
        if (isBound) {
            when (v.id) {
                R.id.btnAdd -> {
                    resultStr = myBoundService?.add(num1, num2).toString()
                }
                R.id.btnSub -> {
                    resultStr = myBoundService?.sub(num1, num2).toString()
                }
                R.id.btnMul -> {
                    resultStr = myBoundService?.mul(num1, num2).toString()
                }
                R.id.btnDel -> {
                    resultStr = myBoundService?.div(num1, num2).toString()
                }
            }
            txvResult.text = resultStr
        }
    }
}