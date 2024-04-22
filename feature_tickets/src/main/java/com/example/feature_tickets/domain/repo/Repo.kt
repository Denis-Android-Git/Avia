package com.example.feature_tickets.domain.repo

import com.example.feature_tickets.domain.model.Offer
import com.example.feature_tickets.domain.model.TicketsOffer

interface Repo {
    suspend fun getList(): List<Offer>
    suspend fun getTickets(): List<TicketsOffer>
}