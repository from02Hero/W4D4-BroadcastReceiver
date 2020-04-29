package com.example.w4d4_broadcastreceiver

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

class MyReceiver : BroadcastReceiver() {

    // String constant that defines the custom broadcast Action.
    companion object {
        val ACTION_CUSTOM_BROADCAST: String =
            BuildConfig.APPLICATION_ID.toString() + ".ACTION_CUSTOM_BROADCAST"
    }

    /**
     * This callback method gets called when the Broadcast Receiver receives a
     * broadcast that it is registered for.
     *
     * @param context The context in which broadcast receiver is running.
     * @param intent The broadcast is delivered in the form of an intent which
     * contains the broadcast action.
     */
    override fun onReceive(context: Context, intent: Intent) {
        val intentAction = intent.action
        if (intentAction != null) {
            var toastMessage = context.getString(R.string.unknown_action)
            when (intentAction) {
                Intent.ACTION_POWER_CONNECTED -> toastMessage =
                    context.getString(R.string.power_connected)
                Intent.ACTION_POWER_DISCONNECTED -> toastMessage =
                    context.getString(R.string.power_disconnected)
                ACTION_CUSTOM_BROADCAST -> toastMessage =
                    context.getString(R.string.custom_broadcast_toast)
            }
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }
}
