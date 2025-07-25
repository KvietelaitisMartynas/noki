package com.marthynas.noki.dialogs

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.DialogProperties
import com.marthynas.noki.alarm.AlarmEvent
import com.marthynas.noki.alarm.AlarmState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.marthynas.noki.CustomTextBox
import com.marthynas.noki.CustomTimeBox
import com.marthynas.noki.ui.theme.NokiTheme

//A composable object for the use as a dialog box for the alarm
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAlarmDialog(
    state: AlarmState,
    onEvent: (AlarmEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler(onBack = { onEvent(AlarmEvent.HideDialog) })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { onEvent(AlarmEvent.HideDialog) }
            },
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = RectangleShape,
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary),
            color = MaterialTheme.colorScheme.background,
            tonalElevation = 0.dp,
            shadowElevation = 0.dp,
            modifier = modifier // Use the modifier passed into the function
                .padding(16.dp)
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { /* Consume the click */ }
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "new alarm")
                Spacer(modifier = Modifier.padding(12.dp))
                CustomTextBox(state.label, { onEvent(AlarmEvent.SetLabel(it)) }, "label")

                Spacer(modifier = Modifier.padding(12.dp))
                CustomTimeBox(state.label, { onEvent(AlarmEvent.SetHour(it.toInt())) }, "hour")

            }
        }
    }
}