package com.example.feature_tickets.domain.model

data class Ticket(
    val arrival: Arrival,
    val badge: String?,
    val company: String,
    val departure: Departure,
    val hand_luggage: HandLuggage,
    val has_transfer: Boolean,
    val has_visa_transfer: Boolean,
    val id: Int,
    val is_exchangable: Boolean,
    val is_returnable: Boolean,
    val luggage: Luggage,
    val price: Price,
    val provider_name: String
)