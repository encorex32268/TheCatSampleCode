package com.lihan.thecatsamplecode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lihan.thecatsamplecode.presentation.MainScreen
import com.lihan.thecatsamplecode.presentation.MainViewModel
import com.lihan.thecatsamplecode.presentation.UiEvent
import com.lihan.thecatsamplecode.ui.theme.TheCatSampleCodeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheCatSampleCodeTheme {
                val viewModel = hiltViewModel<MainViewModel>()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = viewModel) {
                    viewModel.uiEvent.collectLatest {
                        when(it){
                            UiEvent.Failed -> {
                                println("Failed")
                            }
                            UiEvent.Success -> {
                                println("Success")
                            }
                        }
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                        ,
                        state = state,
                        onEvent = viewModel::onEvent
                    )
                }
            }
        }
    }
}
