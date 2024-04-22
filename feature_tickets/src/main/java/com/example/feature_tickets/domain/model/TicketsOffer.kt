package com.example.feature_tickets.domain.model

data class TicketsOffer(
    val id: Int,
    val price: PriceX,
    val time_range: List<String>,
    val title: String
)