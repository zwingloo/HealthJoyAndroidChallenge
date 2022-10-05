package com.axel.healthjoychallenge.data.datasource.rest.entities

import com.google.gson.annotations.SerializedName

data class UserRestEntity(
    @SerializedName("avatar_url")
    val avatarUrl: String?,

    @SerializedName("username")
    val username: String?,

    @SerializedName("description")
    val description: String?
)
