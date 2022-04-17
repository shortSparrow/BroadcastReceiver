package com.example.broadcast

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlin.concurrent.thread

class MyService : Service() {

    private val localBroadcastReceiver by lazy {
        LocalBroadcastManager.getInstance(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        thread {
            for (i in 0..10) {
                Thread.sleep(1_000)

                Intent("loaded").apply {
                    putExtra("percent", i * 10)
                    localBroadcastReceiver.sendBroadcast(this) // Кожну секунду буду надсилатися Broadcast з відсотками, 10, 20, 30 ...
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}