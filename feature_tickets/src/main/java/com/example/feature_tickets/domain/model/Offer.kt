package com.example.feature_tickets.domain.model

data class Offer(
    val id: Int,
    val price: Price,
    val title: String,
    val town: String
)