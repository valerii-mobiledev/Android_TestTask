package com.example.ironsourcetesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ironsourcetesttask.features.boot_completed_tracking.view.BootInformationViewModel
import com.example.ironsourcetesttask.features.boot_completed_tracking.view.ViewState
import com.example.ironsourcetesttask.ui.theme.IronSourceTestTaskTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var viewModel: BootInformationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IronSourceTestTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenContent(viewModel = viewModel)
                }
            }
        }
    }

    @Composable
    fun ScreenContent(viewModel: BootInformationViewModel) {
        val text = viewModel.text.value
        when(text) {
            is ViewState.Data -> Text(text = text.text)
            ViewState.Loading -> Text("No data")
        }
    }
}