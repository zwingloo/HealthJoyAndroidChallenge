package com.axel.healthjoychallenge.data.datasource.rest.store

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.axel.healthjoychallenge.data.datasource.rest.ApiService
import com.axel.healthjoychallenge.data.datasource.rest.entities.PostRestEntity
import com.axel.healthjoychallenge.util.Constants.ITEMS_PER_PAGE
import com.axel.healthjoychallenge.util.Constants.TAG
import com.google.gson.GsonBuilder

class PostPagingDataSource(
    private val apiKey: String,
    private val query: String,
    private val service: ApiService
) : PagingSource<Int, PostRestEntity>() {

    override fun getRefreshKey(state: PagingState<Int, PostRestEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostRestEntity> {
        val pageNumber = params.key ?: 0
        return try {
            val response = service.getPost(apiKey, query, ITEMS_PER_PAGE, pageNumber)
//            val response = service.getPost(apiKey, "cheeseburger", ITEMS_PER_PAGE, pageNumber)
            val pagedResponse = response.body()
            val data = pagedResponse?.data

            Log.d(TAG, "data -> ${ GsonBuilder().create().toJson(data) }")

            val totalCount = pagedResponse?.pagination?.totalCount

            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = if (pageNumber == 0) null else pageNumber - ITEMS_PER_PAGE,
                nextKey = if (pageNumber == totalCount) null else pageNumber + ITEMS_PER_PAGE
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}