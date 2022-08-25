package com.xinkev.githubusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.xinkev.githubusers.data.remote.GithubApi
import com.xinkev.githubusers.data.remote.models.UsersResponse
import com.xinkev.githubusers.ui.theme.GHUsersTheme
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var githubApi: GithubApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("Timber planted!")
        setContent {
            GHUsersTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var apiResponse by remember {
                        mutableStateOf<Response<List<UsersResponse>>?>(null)
                    }
                    LaunchedEffect(Unit) {
                        apiResponse = githubApi.userList()
                    }
                    if (apiResponse == null) {
                        CircularProgressIndicator()
                    } else {
                        if (apiResponse!!.isSuccessful) {
                            LazyColumn {
                                items(items = apiResponse!!.body()!!, key = { it.id }) {
                                    Text(text = it.login ?: "Unknown")
                                }
                            }
                        } else {
                            Text(text = "Error!")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GHUsersTheme {
        Greeting("Android")
    }
}
