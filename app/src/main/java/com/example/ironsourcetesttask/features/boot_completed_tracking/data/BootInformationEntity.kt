package com.example.ironsourcetesttask.features.boot_completed_tracking.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "boot_information_entity")
data class BootInformationEntity(
    @PrimaryKey val uuid: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
)