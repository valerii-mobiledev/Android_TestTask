package com.example.ironsourcetesttask

import android.app.Application
import com.example.ironsourcetesttask.features.boot_completed_tracking.BootCompletedTrackingUseCase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App: Application() {

    @Inject lateinit var bootCompletedTrackingUseCase: BootCompletedTrackingUseCase
    override fun onCreate() {
        super.onCreate()
        bootCompletedTrackingUseCase.scheduleUpdateBootInformationNotification()
    }
}