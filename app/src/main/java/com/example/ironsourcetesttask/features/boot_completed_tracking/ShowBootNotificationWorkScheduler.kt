package com.example.ironsourcetesttask.features.boot_completed_tracking

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.ironsourcetesttask.common.db.AppDatabaseProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject


interface ShowBootNotificationWorkSchedulerInterface {
    fun scheduleUpdateBootNotificationOnceIn15m()
}

class ShowBootNotificationWorkScheduler @Inject constructor(
    @ApplicationContext appContext: Context,
) :
    ShowBootNotificationWorkSchedulerInterface {



    companion object {
        val workName = "UpdateBootNotificationTask"
    }

    private val workManager = WorkManager.getInstance(appContext)
    override fun scheduleUpdateBootNotificationOnceIn15m() {

        // TODO: implement worker
//        val periodicWorkRequest = PeriodicWorkRequestBuilder<MyWorker>(15, TimeUnit.MINUTES)
//            .build()
//        workManager.enqueue(periodicWorkRequest)
    }
}

class DailyWorker @Inject constructor(context: Context, params: WorkerParameters) :
    Worker(context, params) {

    override fun doWork(): Result {
        return Result.success()
    }
}