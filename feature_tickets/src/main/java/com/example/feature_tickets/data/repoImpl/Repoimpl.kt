package com.example.feature_tickets.data.repoImpl

import com.example.feature_tickets.data.jsonrepo.JsonRepo
import com.example.feature_tickets.domain.model.Offer
import com.example.feature_tickets.domain.model.Ticket
import com.example.feature_tickets.domain.model.TicketsOffer
import com.example.feature_tickets.domain.repo.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repoimpl(
    private val jsonRepo: JsonRepo
) : Repo {
    override suspend fun getList(): List<Offer> {
        return withContext(Dispatchers.IO) {
            jsonRepo.offerList()
        }
    }

    override suspend fun getTickets(): List<TicketsOffer> {
        return withContext(Dispatchers.IO) {
            jsonRepo.tickets()
        }
    }

    override suspend fun getLastTickets(): List<Ticket> {
        return withContext(Dispatchers.IO) {
            jsonRepo.lastTickets()
        }
    }
}
