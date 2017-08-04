package com.a3callistos.service1.activity

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.a3callistos.service1.R

/**
 * Created by Bibesh on 8/4/17.
 */
class MyMessagnerActivity : AppCompatActivity() {
    var txvResult: TextView? = null
    var isBound = false

    val mServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound = false
        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            isBound = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messanger)
        txvResult = findViewById(R.id.txvResult) as TextView
    }

    fun performAddOperation(v: View) {
        val etNum1: EditText = findViewById(R.id.etNumOne) as EditText
        val etNum2: EditText = findViewById(R.id.etNumTwo) as EditText

        val num1 = etNum1.text.toString().toInt()
        val num2 = etNum2.text.toString().toInt()
    }

    fun bindService(v: View) {

    }

    fun unbindService(v: View) {

    }
}