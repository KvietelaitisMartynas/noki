package com.marthynas.noki.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import com.marthynas.noki.alarm.AlarmEvent
import com.marthynas.noki.alarm.AlarmState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.marthynas.noki.CustomTextBox
import com.marthynas.noki.ui.theme.NokiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAlarmDialog(
    state: AlarmState,
    onEvent: (AlarmEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    BasicAlertDialog(
        onDismissRequest = {
            onEvent(AlarmEvent.HideDialog)
        },
        modifier = modifier,
        content = {
            NokiTheme {
                Surface(
                    shape = RectangleShape, // Sharp corners
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary), // Clear black border
                    color = MaterialTheme.colorScheme.background, // Pure white background
                    tonalElevation = 0.dp,
                    shadowElevation = 0.dp,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ){
                        Text(text = "new alarm")

                        Spacer(modifier = Modifier.padding(12.dp))

                        CustomTextBox(state.label, {onEvent(AlarmEvent.SetLabel(it))}, "label")
                        /*TextField(
                            value = state.label,
                            onValueChange = {
                                onEvent(AlarmEvent.SetLabel(it))
                            },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )*/
                    }
                }
            }
        }
    )
}