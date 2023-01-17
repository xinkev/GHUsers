package com.xinkev.ghusers.kmp.android.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import coil.compose.rememberAsyncImagePainter

@Composable
fun IconWithText(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    text: String,
    icon: ImageVector
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Stars"
        )
        Text(text = text, style = textStyle)
    }
}

@Composable
fun IconWithText(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    text: String,
    @DrawableRes icon: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = rememberAsyncImagePainter(model = icon),
            contentDescription = "Stars"
        )
        Text(text = text, style = textStyle)
    }
}
