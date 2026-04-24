package com.example.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CounterScreen()
                }
            }
        }
    }
}

@Composable
fun CounterScreen(viewModel: CounterViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Счётчик: ${uiState.count}", style = MaterialTheme.typography.displayLarge)
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { viewModel.decrement() }) { Text("-") }
            Button(onClick = { viewModel.reset() }) { Text("Сброс") }
            Button(onClick = { viewModel.increment() }) { Text("+") }
        }
        Text("История", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(uiState.history) { record ->
                Card(Modifier.fillMaxWidth()) {
                    Text(record, modifier = Modifier.padding(12.dp))
                }
            }
        }
    }
}