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
import com.marthynas.noki.ui.theme.NokiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAlarmDialog(
    state: AlarmState,
    onEvent: (AlarmEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    // This is crucial for making your custom dialog feel native.
    // It intercepts the system back button/gesture and calls your dismiss event.
    BackHandler(onBack = { onEvent(AlarmEvent.HideDialog) })

    // The full-screen Box that acts as your transparent "scrim".
    Box(
        modifier = Modifier
            .fillMaxSize()
            // This detects clicks on the transparent area *outside* your dialog.
            .pointerInput(Unit) {
                detectTapGestures { onEvent(AlarmEvent.HideDialog) }
            },
        contentAlignment = Alignment.Center
    ) {
        // Your dialog's content area.
        Surface(
            shape = RectangleShape,
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary),
            color = MaterialTheme.colorScheme.background,
            tonalElevation = 0.dp,
            shadowElevation = 0.dp,
            modifier = modifier // Use the modifier passed into the function
                .padding(16.dp)
                .fillMaxWidth()
                // This modifier is the key fix:
                // 1. It makes the Surface clickable.
                // 2. Its empty action `{}` means nothing happens when you click it.
                // 3. It STOPS the click from propagating to the parent Box,
                //    so clicking the dialog doesn't dismiss it.
                // 4. It does NOT block clicks from reaching child composables
                //    like your TextField.
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null // No visual ripple effect on the container.
                ) { /* Consume the click */ }
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "new alarm")
                Spacer(modifier = Modifier.padding(12.dp))
                CustomTextBox(state.label, { onEvent(AlarmEvent.SetLabel(it)) }, "label")
            }
        }
    }
}


/*{
/*


    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { onEvent(AlarmEvent.HideDialog) }
            },
        contentAlignment = Alignment.Center
    ) {
        NokiTheme {
            Surface(
                shape = RectangleShape,
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary),
                color = MaterialTheme.colorScheme.background,
                tonalElevation = 0.dp,
                shadowElevation = 0.dp,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null // No ripple effect on the dialog container
                    ) {
                        */
/* Do nothing. This just consumes the click. *//*

                    }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "new alarm")
                    Spacer(modifier = Modifier.padding(12.dp))
                    CustomTextBox(state.label, { onEvent(AlarmEvent.SetLabel(it)) }, "label")
                }
            }
        }
    }
*/

    BasicAlertDialog(
        onDismissRequest = {
            onEvent(AlarmEvent.HideDialog)
        },
        modifier = modifier,
        properties = DialogProperties(
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = true,

        ),
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
}*/