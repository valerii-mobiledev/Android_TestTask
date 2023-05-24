package com.example.ironsourcetesttask.features.boot_completed_tracking.data

import com.example.ironsourcetesttask.common.db.AppDatabase
import com.example.ironsourcetesttask.common.db.AppDatabaseProvider
import com.example.ironsourcetesttask.features.boot_completed_tracking.BootInformation
import com.example.ironsourcetesttask.features.boot_completed_tracking.domain.BootDomainTransformations
import java.util.UUID
import javax.inject.Inject

class BootInformationRepository @Inject constructor(appDatabase: AppDatabaseProvider) {
    val dao = appDatabase.db.bootInformationDao()

    suspend fun writeBootInformation(bootInformation: BootInformation) {
        dao.insertAll(
            BootDomainTransformations.convertBootInformationToEntity(
                BootInformation(bootInformation.id, bootInformation.unixTimestamp)
            )
        )
    }

    suspend fun getOrderedBootInformation(): List<BootInformation> {
        return dao.getAll().map { BootDomainTransformations.convertEntityToBootInformation(it) }
    }
}