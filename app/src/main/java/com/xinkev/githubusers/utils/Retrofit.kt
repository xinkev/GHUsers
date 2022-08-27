package com.xinkev.githubusers.utils

import com.xinkev.githubusers.models.Outcome
import retrofit2.Response

suspend fun <Remote, Domain> handleApi(
    map: (Remote) -> Domain,
    block: suspend () -> Response<Remote>
): Outcome<Domain> {
    val result = runCatching { block() }
    return when {
        result.isSuccess -> {
            val response = result.getOrThrow()
            when {
                response.isSuccessful -> Outcome.Success(map(response.body()!!))
                else -> {
                    Outcome.Failure(response.message())
                }
            }
        }
        else -> {
            Outcome.Failure(result.exceptionOrNull()!!.message!!)
        }
    }
}
