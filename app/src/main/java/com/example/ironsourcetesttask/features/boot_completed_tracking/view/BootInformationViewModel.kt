package com.example.ironsourcetesttask.features.boot_completed_tracking.view

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ironsourcetesttask.features.boot_completed_tracking.BootInformation
import com.example.ironsourcetesttask.features.boot_completed_tracking.data.BootInformationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

sealed class ViewState {
    class Data(val text: String) : ViewState()
    object Loading : ViewState()
}

class BootInformationViewModel @Inject constructor(private val bootInformationRepository: BootInformationRepository) :
    ViewModel() {

    val text = mutableStateOf<ViewState>(ViewState.Loading)

    init {
        viewModelScope.launch {
            val bootInformation = bootInformationRepository.getOrderedBootInformation()
            val text = bootInformation.zip(Array(bootInformation.size) { it }).map { bootRecord ->
                "${bootRecord.second} - ${bootRecord.first.unixTimestamp}"
            }.joinToString("\n")
            if (bootInformation.isEmpty()) {
                this@BootInformationViewModel.text.value = ViewState.Data("No Data")
                return@launch
            }
            this@BootInformationViewModel.text.value = ViewState.Data(text)
        }
    }
}