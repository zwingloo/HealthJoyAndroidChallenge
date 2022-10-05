package com.axel.healthjoychallenge.data.datasource.rest.entities

import com.google.gson.annotations.SerializedName

data class ImageRestEntity(
    @SerializedName("original")
    val original: OriginalRestEntity
)
