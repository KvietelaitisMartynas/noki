package com.marthynas.noki.screens

import com.marthynas.noki.alarm.AlarmEvent
import com.marthynas.noki.alarm.AlarmState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marthynas.noki.CustomButton
import com.marthynas.noki.CustomTopBar
import com.marthynas.noki.R
import com.marthynas.noki.ui.theme.NokiTheme
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.unit.sp
import com.marthynas.noki.dialogs.AddAlarmDialog


@Composable
fun HomeScreen(state: AlarmState, onEvent: (AlarmEvent) -> Unit, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CustomTopBar(title = "noki")
        },

        floatingActionButton = {
/*            FloatingActionButton(onClick = {
                onEvent(AlarmEvent.ShowDialog)
            }) {
                Icon(Icons.Default.Add, contentDescription = "add alarm")
            }*/
            CustomButton(text = "+", onClick = {onEvent(AlarmEvent.ShowDialog)})
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.alarms) { alarm ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Column {
                        Text(
                            text = "${alarm.hour}:${alarm.minute}", fontSize = 40.sp
                        )
                        Text(
                            text = alarm.label, fontSize = 20.sp
                        )
                    }
                }
            }
        }

        if (state.isAddingAlarm) {
            AddAlarmDialog(state = state, onEvent = onEvent)
        }
    }
}

/*

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marthynas.noki.CustomButton
import com.marthynas.noki.CustomTopBar
import com.marthynas.noki.R
import com.marthynas.noki.ui.theme.NokiTheme

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold (
        topBar = {
            CustomTopBar(title = "noki")
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "home screen")
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(text = "go to details", onClick = {navController.navigate("details")})
        }
    }

*/
/*    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    )
    {

    }*//*

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NokiTheme {
        HomeScreen(rememberNavController())
    }
}*/
