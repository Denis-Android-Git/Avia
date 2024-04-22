package com.example.feature_tickets.domain.usecase

import com.example.feature_tickets.domain.model.Offer
import com.example.feature_tickets.domain.repo.Repo

class GetListUseCase(
    private val repo: Repo
) {
    suspend fun execute(): List<Offer> {
        return repo.getList()
    }
}