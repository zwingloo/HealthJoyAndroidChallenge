package com.axel.healthjoychallenge.ui.home

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.axel.healthjoychallenge.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val currentQuery = MutableLiveData("")

    fun getPost(apiKey: String) = currentQuery.switchMap { queryString ->
        homeRepository.getPost(apiKey, queryString).cachedIn(viewModelScope)
    }

    fun searchPost(query: String) {
        currentQuery.value = query
    }
}