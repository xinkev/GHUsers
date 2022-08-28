package com.xinkev.githubusers.userList.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OutlinedText(
    modifier: Modifier = Modifier,
    color: Color,
    text: String
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(25),
        border = BorderStroke(width = 0.7.dp, color = color),
        color = color.copy(alpha = 0.1f),
        contentColor = color
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
            style = MaterialTheme.typography.overline
        )
    }
}
