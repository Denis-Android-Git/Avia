package com.example.feature_tickets.domain.usecase

import com.example.feature_tickets.domain.model.Ticket
import com.example.feature_tickets.domain.repo.Repo

class GetAllTicketsUseCase(
    private val repo: Repo
) {
    suspend fun execute(): List<Ticket> {
        return repo.getLastTickets()
    }
}