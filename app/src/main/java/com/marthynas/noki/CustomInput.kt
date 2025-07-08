package com.marthynas.noki

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

import android.util.Log

@Composable
fun CustomTextBox(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    Column {
        Text(text = label, color = MaterialTheme.colorScheme.onPrimary, fontSize = MaterialTheme.typography.bodySmall.fontSize)
        Box(
            modifier = Modifier
                .border(2.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(0.dp))
                .padding(horizontal = 8.dp, vertical = 8.dp) // set padding yourself
        )
	{
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onPrimary),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )
        }
    }
}

/**
 * A visual transformation that formats the input text as a time (HH:MM).
 * It expects a string of up to 4 digits.
 */
class TimeVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // Build the formatted text
        val formattedText = buildString {
            val digits = text.text.filter { it.isDigit() }
            for (i in digits.indices) {
                append(digits[i])
                if (i == 1 && i < digits.length - 1) {
                    append(':')
                }
            }
        }

        // Define how the cursor position maps from the original text to the transformed text
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                // If the cursor is after the second digit, we add 1 for the colon
                if (offset > 2) return offset + 1
                return offset
            }

            override fun transformedToOriginal(offset: Int): Int {
                // If the cursor is after the colon, we subtract 1 to get the original position
                if (offset > 2) return offset - 1
                return offset
            }
        }

        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }
}

@Composable
fun CustomTimeBox(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier) {
	    Text(
            text = label,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = MaterialTheme.typography.bodySmall.fontSize
        )
        Box(
            modifier = Modifier
                .border(2.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(0.dp))
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            BasicTextField(
                value = value,
                onValueChange = { newText ->
                    // Only allow up to 4 digits to be entered (for HHmm)
                    if (newText.length <= 4 && newText.all { it.isDigit() }) {
                        onValueChange(newText)
                    }
                },
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onPrimary),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = TimeVisualTransformation()
            ) { innerTextField ->
                // Custom placeholder implementation
                Box(Modifier.fillMaxWidth()) {
                    if (value.isEmpty()) {
                        Text(
                            text = "HH:MM", // Use a format-specific placeholder
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.onPrimary.copy(
                                    alpha = 0.5f
                                )
                            )
                        )
                    }
                    innerTextField()
                }
            }
        }
    }
}
