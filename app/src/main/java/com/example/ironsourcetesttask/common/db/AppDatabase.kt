package com.example.ironsourcetesttask.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ironsourcetesttask.features.boot_completed_tracking.data.BootInformationDao
import com.example.ironsourcetesttask.features.boot_completed_tracking.data.BootInformationEntity

@Database(entities = [BootInformationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bootInformationDao(): BootInformationDao
}