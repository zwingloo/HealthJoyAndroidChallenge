package com.axel.healthjoychallenge.data.datasource.rest.response

abstract class ResponseHandler<T : Any> {
    val data: T? = null

    val pagination: Pagination? = null

    abstract fun toModel(): T?
}