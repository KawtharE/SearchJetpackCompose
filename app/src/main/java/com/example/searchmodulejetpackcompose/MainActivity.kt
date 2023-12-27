package com.example.searchmodulejetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.searchmodulejetpackcompose.ui.theme.SearchModuleJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchModuleJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mainViewModel = viewModel<MainViewModel>()
                    val searchInput by mainViewModel.searchInput.collectAsState()
                    val students by mainViewModel.students.collectAsState()
                    val isSearching by mainViewModel.isSearching.collectAsState()
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp)
                    ) {
                        TextField(
                            value = searchInput,
                            onValueChange = mainViewModel::onSearchInput,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(text = "Search student...") }
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        if (isSearching) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            }
                        } else {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            ) {
                                items(students) { student ->
                                    Text(
                                        text = "${student.firstName} ${student.lastName} (${student.university})",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 15.dp)
                                    )

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SearchModuleJetpackComposeTheme {
        Greeting("Android")
    }
}