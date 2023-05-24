package com.example.ironsourcetesttask.features.boot_completed_tracking

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.compose.ui.res.booleanResource
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class BootCompletedBroadcastReceiver: BroadcastReceiver() {
    @Inject lateinit var bootCompletedTrackingUseCase: BootCompletedTrackingUseCase

    // TODO: troubleshoot why not called.
    override fun onReceive(context: Context, intent: Intent) {
        if (!intent.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            return
        }

        runBlocking {
            launch {
                bootCompletedTrackingUseCase.recordBootCompletedEvent()
            }
        }

    }
}