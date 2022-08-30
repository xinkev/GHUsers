package com.xinkev.githubusers.userList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.xinkev.githubusers.R
import com.xinkev.githubusers.models.User
import com.xinkev.githubusers.ui.composables.ErrorView
import com.xinkev.githubusers.ui.composables.Loading
import com.xinkev.githubusers.userList.composables.UserList
import kotlin.math.roundToInt

@Composable
fun UserListScreen(
    vm: UserListViewModel,
    navigateToDetails: (User) -> Unit
) {
    val userList = vm.userList.collectAsLazyPagingItems()
    val toolbarHeight = 48.dp
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.value + delta
                toolbarOffsetHeightPx.value = newOffset.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        when (val loadState = userList.loadState.refresh) {
            is LoadState.Loading -> Loading(modifier = Modifier.fillMaxSize())
            is LoadState.Error -> {
                ErrorView(
                    modifier = Modifier.fillMaxSize(),
                    throwable = loadState.error,
                    onClick = userList::retry
                )
            }
            is LoadState.NotLoading -> UserList(
                toolbarHeight = toolbarHeight,
                items = userList,
                onEntryClick = navigateToDetails
            )
        }
        Toolbar(height = toolbarHeight, offset = toolbarOffsetHeightPx.value)
    }
}

@Composable
private fun Toolbar(height: Dp, offset: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .offset { IntOffset(x = 0, y = offset.roundToInt()) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            stringResource(R.string.app_name),
            style = MaterialTheme.typography.h5
        )
    }
}
