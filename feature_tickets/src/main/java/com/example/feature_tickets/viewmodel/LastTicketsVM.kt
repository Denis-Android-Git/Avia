package com.example.feature_tickets.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_tickets.domain.model.Ticket
import com.example.feature_tickets.domain.usecase.GetAllTicketsUseCase
import kotlinx.coroutines.launch

class LastTicketsVM(
    private val getAllTicketsUseCase: GetAllTicketsUseCase
) : ViewModel() {
    private var _list = MutableLiveData<List<Ticket>>(emptyList())
    val list: LiveData<List<Ticket>> = _list

    init {
        viewModelScope.launch {
            _list.value = getAllTicketsUseCase.execute()
        }
    }
}