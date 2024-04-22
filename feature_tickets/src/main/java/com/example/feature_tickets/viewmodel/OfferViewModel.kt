package com.example.feature_tickets.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_tickets.domain.model.Offer
import com.example.feature_tickets.domain.usecase.GetListUseCase
import kotlinx.coroutines.launch

class OfferViewModel(
    private val getListUseCase: GetListUseCase
) : ViewModel() {
    private var _list = MutableLiveData<List<Offer>>(emptyList())
    val list: LiveData<List<Offer>> = _list

    init {
        viewModelScope.launch {
            _list.value = getListUseCase.execute()
        }
    }
}