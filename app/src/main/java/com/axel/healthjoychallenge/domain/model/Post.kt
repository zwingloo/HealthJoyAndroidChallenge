package com.axel.healthjoychallenge.domain.model

data class Post(
    val id: String,
    val user: User,
    val embedUrl: String
)