package com.axel.healthjoychallenge.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.axel.healthjoychallenge.data.datasource.rest.ApiService
import com.axel.healthjoychallenge.data.datasource.rest.entities.PostRestEntity
import com.axel.healthjoychallenge.data.datasource.rest.store.PostPagingDataSource
import com.axel.healthjoychallenge.domain.repository.IHomeRepository
import com.axel.healthjoychallenge.util.Constants.ITEMS_PER_PAGE
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiService: ApiService) : IHomeRepository {
    override fun getPost(apiKey: String, query: String): LiveData<PagingData<PostRestEntity>> = Pager(
        config = PagingConfig(ITEMS_PER_PAGE, prefetchDistance = 2),
        pagingSourceFactory = { PostPagingDataSource(apiKey, query, apiService) }
    ).liveData
}