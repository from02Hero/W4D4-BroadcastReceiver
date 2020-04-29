package com.example.w4d4_broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {

    private var mReceiver: MyReceiver = MyReceiver()

    // String constant that defines the custom broadcast Action.
    private val ACTION_CUSTOM_BROADCAST: String =
        BuildConfig.APPLICATION_ID.toString() + ".ACTION_CUSTOM_BROADCAST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Define the IntentFilter.
        val filter = IntentFilter()
        // Add system broadcast actions sent by the system when the power is
        // connected and disconnected.
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)

        // Register the receiver using the activity context, passing in the
        // IntentFilter.
        this.registerReceiver(mReceiver, filter)

        // Register the receiver to receive custom broadcast.
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(mReceiver, IntentFilter(ACTION_CUSTOM_BROADCAST))
    }

    /**
     * Click event handler for the button, that sends custom broadcast using the
     * LocalBroadcastManager.
     */
    fun sendCustomBroadcast(view: View) {
        val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)
        LocalBroadcastManager.getInstance(this)
            .sendBroadcast(customBroadcastIntent)
    }

    override fun onDestroy() { // Unregister the receivers.
        unregisterReceiver(mReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver)
        super.onDestroy()
    }
}
