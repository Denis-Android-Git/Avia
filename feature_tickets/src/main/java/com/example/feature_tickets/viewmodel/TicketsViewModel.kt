package com.example.feature_tickets.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_tickets.domain.model.TicketsOffer
import com.example.feature_tickets.domain.usecase.GetTicketsUseCase
import kotlinx.coroutines.launch

class TicketsViewModel(
    private val getTicketsUseCase: GetTicketsUseCase
) : ViewModel() {
    private var _list = MutableLiveData<List<TicketsOffer>>(emptyList())
    val list: LiveData<List<TicketsOffer>> = _list

    init {
        viewModelScope.launch {
            _list.value = getTicketsUseCase.execute()
        }
    }
}