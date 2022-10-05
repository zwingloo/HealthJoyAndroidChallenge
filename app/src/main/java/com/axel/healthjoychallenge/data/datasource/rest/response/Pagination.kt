package com.axel.healthjoychallenge.data.datasource.rest.response

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("total_count")
    val totalCount: Int,

    val count: Int,

    val offset: Int
)
