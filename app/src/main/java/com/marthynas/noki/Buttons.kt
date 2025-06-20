package com.marthynas.noki

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton (
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
/*    Button(
        onClick = onClick,
        modifier = modifier
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onPrimary, // or any color you like
                shape = RoundedCornerShape(0.dp)
            ),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(0.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )*/

    Box(
        modifier = Modifier
            .border(2.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(0.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp) // set padding yourself
    ) {
        Text(text = text, color = MaterialTheme.colorScheme.onPrimary)
    }

}