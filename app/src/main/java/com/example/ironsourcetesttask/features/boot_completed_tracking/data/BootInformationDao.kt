package com.example.ironsourcetesttask.features.boot_completed_tracking.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BootInformationDao {
    @Query("SELECT * FROM boot_information_entity")
    suspend fun getAll(): List<BootInformationEntity>

    @Query("SELECT * FROM boot_information_entity WHERE uuid = :uuid")
    suspend fun getByUuid(uuid: String): BootInformationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: BootInformationEntity)
}