package com.axel.healthjoychallenge.data.datasource.rest.entities

import com.google.gson.annotations.SerializedName

data class PostRestEntity(
    val id: String,

    @SerializedName("user")
    val user: UserRestEntity?,

    @SerializedName("images")
    val images: ImageRestEntity
)
