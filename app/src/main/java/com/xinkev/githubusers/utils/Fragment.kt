package com.xinkev.githubusers.utils // ktlint-disable filename

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
import androidx.fragment.app.Fragment
import com.xinkev.githubusers.ui.theme.GHUsersTheme

fun Fragment.composeView(
    compositionStrategy: ViewCompositionStrategy = DisposeOnViewTreeLifecycleDestroyed,
    content: @Composable () -> Unit
) = ComposeView(requireContext()).apply {
    setViewCompositionStrategy(compositionStrategy)
    setContent {
        GHUsersTheme {
            content()
        }
    }
}
