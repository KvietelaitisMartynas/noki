package com.marthynas.noki.screens

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

/*    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    )
    {

    }*/
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NokiTheme {
        HomeScreen(rememberNavController())
    }
}