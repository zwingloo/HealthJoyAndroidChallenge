package com.axel.healthjoychallenge.data.datasource.rest.store

import com.axel.healthjoychallenge.util.Resource
import retrofit2.Response

abstract class BaseDataStore {
    private val TAG = "BaseDataStore"

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            val body = response.body()
            if (response.isSuccessful) {
                if (body != null) return Resource.success(body)
            }

            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.error("Network call has failed for a following reason: $message")
    }
}