package com.xinkev.ghusers.kmp.android.userList.composables

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
fun UserType(
    modifier: Modifier = Modifier,
    color: Color,
    text: String
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(25),
        color = color,
        contentColor = Color.White
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
            style = MaterialTheme.typography.overline
        )
    }
}
