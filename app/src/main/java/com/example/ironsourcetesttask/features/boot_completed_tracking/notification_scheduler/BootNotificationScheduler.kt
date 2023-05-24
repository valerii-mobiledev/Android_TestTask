package com.example.ironsourcetesttask.features.boot_completed_tracking.notification_scheduler

import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.ironsourcetesttask.R
import com.example.ironsourcetesttask.features.boot_completed_tracking.BootInformationNotificationData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BootNotificationScheduler @Inject constructor(@ApplicationContext private val applicationContext: Context) {
    companion object {
        val channelId = "BOOT_NOTIFICATION_CHANNEL_ID"
    }

    fun spawnOrUpdateBootNotification(notificationData: BootInformationNotificationData) {
        val notificationTextContent = createBodyTextForNotificationData(notificationData)

        NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(androidx.loader.R.drawable.notification_icon_background)
            .setContentTitle("IronSource test task")
            .setContentText(notificationTextContent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    }

    private fun createBodyTextForNotificationData(notificationData: BootInformationNotificationData): String {
        return when (notificationData) {
            is BootInformationNotificationData.MultipleBootsDetected -> "Last boots delta time ${notificationData.latestBootInformation.unixTimestamp - notificationData.beforeLatestBootInformation.unixTimestamp}"
            BootInformationNotificationData.NoBootsDetected -> "No boots detected"
            is BootInformationNotificationData.OneBootDetected -> "The boot was detected with the timestamp = ${notificationData.bootInformation.unixTimestamp}"
        }
    }
}