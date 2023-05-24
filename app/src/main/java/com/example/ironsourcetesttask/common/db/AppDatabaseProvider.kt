package com.example.ironsourcetesttask.common.db

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppDatabaseProvider @Inject constructor(@ApplicationContext appContext: Context) {
    val db = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java, "database",
    ).build()
}