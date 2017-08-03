package com.a3callistos.service1.service

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.os.ResultReceiver

/**
 * Created by Bibesh on 8/3/17.
 */

class MyIntentService : IntentService {
    constructor() : super("My Worker Therad")

    override fun onHandleIntent(intent: Intent?) {
        val resultReceiver: ResultReceiver = intent!!.getParcelableExtra("receiver")
        Thread.sleep(5000)
        println(MyIntentService::class.java.simpleName + " " + Thread.currentThread().name)
        var bundle: Bundle = Bundle();
        bundle.putString("key", "Value");
        resultReceiver.send(10, bundle)
    }

}