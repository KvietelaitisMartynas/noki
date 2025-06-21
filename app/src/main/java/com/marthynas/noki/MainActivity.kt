package com.marthynas.noki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.marthynas.noki.screens.DetailsScreen
import com.marthynas.noki.screens.HomeScreen
import com.marthynas.noki.ui.theme.NokiTheme

import com.marthynas.noki.database.AlarmDatabase
import com.marthynas.noki.viewModels.AlarmViewModel

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AlarmDatabase::class.java,
            "alarms.db"
        ).build()
    }

    private val viewModel by viewModels<AlarmViewModel> (
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return AlarmViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NokiTheme {
                val state = viewModel.state.collectAsState()
                HomeScreen(state = state.value, onEvent = viewModel::onEvent)

/*                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingScreen(
                        name = "Martynas",
                        modifier = Modifier.padding(innerPadding)
                    )
                }*/
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

@Composable
fun StartingTextField()
{
    var textState by remember { mutableStateOf("") }
    TextField(
        value = textState,
        onValueChange = { textState = it },
        label = { Text("Name") }
    )
}

@Composable
fun GreetingScreen(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Greeting(name, modifier)
        Spacer(Modifier.height(32.dp))
        StartingTextField()
    }
}


/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NokiTheme {
        GreetingScreen("Martynas");
    }
}*/
