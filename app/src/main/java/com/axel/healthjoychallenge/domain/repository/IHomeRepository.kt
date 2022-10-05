package com.axel.healthjoychallenge.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.axel.healthjoychallenge.data.datasource.rest.entities.PostRestEntity

interface IHomeRepository {
    fun getPost(apiKey: String, query: String): LiveData<PagingData<PostRestEntity>>
}