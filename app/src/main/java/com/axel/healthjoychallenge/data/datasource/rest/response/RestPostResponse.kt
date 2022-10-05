package com.axel.healthjoychallenge.data.datasource.rest.response

import com.axel.healthjoychallenge.data.datasource.rest.entities.PostRestEntity

class RestPostResponse : ResponseHandler<List<PostRestEntity>>() {
    override fun toModel(): List<PostRestEntity>? {
        return this.data
    }
}