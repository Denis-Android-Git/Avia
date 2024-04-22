package com.example.feature_tickets.domain.usecase

import com.example.feature_tickets.domain.model.TicketsOffer
import com.example.feature_tickets.domain.repo.Repo

class GetTicketsUseCase(
    private val repo: Repo
) {
    suspend fun execute(): List<TicketsOffer> {
        return repo.getTickets()
    }
}