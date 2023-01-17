package com.xinkev.ghusers.kmp.android.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorView(
    modifier: Modifier,
    throwable: Throwable? = null,
    message: String? = null,
    onClick: () -> Unit
) {
    Box(modifier) {
        Column(
            modifier = Modifier
                .clickable(
                    onClick = onClick,
                    indication = null,
                    role = Role.Button,
                    interactionSource = remember { MutableInteractionSource() }
                )
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = Icons.Filled.Error, contentDescription = "Tap to try again")
            Text(
                message ?: throwable?.localizedMessage ?: throwable?.message!!,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}
