package com.example.ironsourcetesttask.features.boot_completed_tracking.domain

import com.example.ironsourcetesttask.features.boot_completed_tracking.BootInformation
import com.example.ironsourcetesttask.features.boot_completed_tracking.data.BootInformationEntity
import java.util.Date

object BootDomainTransformations {
    fun convertEntityToBootInformation(entity: BootInformationEntity): BootInformation {
        return BootInformation(entity.uuid, entity.timestamp)
    }

    fun convertBootInformationToEntity(bootInformation: BootInformation): BootInformationEntity {
        return BootInformationEntity(bootInformation.id, bootInformation.unixTimestamp)
    }
}