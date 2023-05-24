package com.example.ironsourcetesttask.features.boot_completed_tracking

import androidx.compose.runtime.currentRecomposeScope
import com.example.ironsourcetesttask.common.db.AppDatabaseProvider
import com.example.ironsourcetesttask.features.boot_completed_tracking.data.BootInformationRepository
import com.example.ironsourcetesttask.features.boot_completed_tracking.domain.BootDomainTransformations
import com.example.ironsourcetesttask.features.boot_completed_tracking.notification_scheduler.BootNotificationScheduler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID
import javax.inject.Inject

interface BootCompleteTrackingUseCaseInterface {
    suspend fun recordBootCompletedEvent()
    suspend fun showBootInformationNotification()
    fun scheduleUpdateBootInformationNotification()
}

data class BootInformation(
    val id: String,
    val unixTimestamp: Long,
)

sealed class BootInformationNotificationData {
    object NoBootsDetected : BootInformationNotificationData()
    class OneBootDetected(val bootInformation: BootInformation) : BootInformationNotificationData()
    class MultipleBootsDetected(
        val latestBootInformation: BootInformation,
        val beforeLatestBootInformation: BootInformation
    ) : BootInformationNotificationData()
}

class BootCompletedTrackingUseCase @Inject constructor(
    private val bootInformationRepository: BootInformationRepository,
    private val bootNotificationScheduler: BootNotificationScheduler,
    private val showBootNotificationWorkScheduler: ShowBootNotificationWorkScheduler
) :
    BootCompleteTrackingUseCaseInterface {

    //    private val dbScope = Dispatchers.IO
    override suspend fun recordBootCompletedEvent() {
        val currentUnixTimestamp = Date().time
        bootInformationRepository.writeBootInformation(
            BootInformation(
                UUID.randomUUID().toString(),
                currentUnixTimestamp
            )
        )
    }

    override suspend fun showBootInformationNotification() {
        val notificationData = createModelForNotification()
        bootNotificationScheduler.spawnOrUpdateBootNotification(notificationData)
    }

    override fun scheduleUpdateBootInformationNotification() {
        showBootNotificationWorkScheduler.scheduleUpdateBootNotificationOnceIn15m()
    }

    private suspend fun createModelForNotification(): BootInformationNotificationData {
        val bootInformation = bootInformationRepository.getOrderedBootInformation()
        return when (bootInformation.count()) {
            0 -> BootInformationNotificationData.NoBootsDetected
            1 -> {
                val singleBootRecord = bootInformation.firstOrNull()
                    ?: return BootInformationNotificationData.NoBootsDetected
                BootInformationNotificationData.OneBootDetected(singleBootRecord)
            }

            else -> {
                val firstBootRecord = bootInformation.getOrNull(0)
                    ?: return BootInformationNotificationData.NoBootsDetected
                val secondBootRecord = bootInformation.getOrNull(1)
                    ?: return BootInformationNotificationData.NoBootsDetected
                return BootInformationNotificationData.MultipleBootsDetected(
                    firstBootRecord,
                    secondBootRecord
                )
            }
        }
    }


}