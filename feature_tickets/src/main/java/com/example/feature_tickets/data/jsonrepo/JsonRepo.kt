package com.example.feature_tickets.data.jsonrepo

import com.example.feature_tickets.domain.model.Offer
import com.example.feature_tickets.domain.model.TicketsOffer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonRepo {
    fun offerList(): List<Offer> {
        val jsonString = """
        [
            {
                "id": 1,
                "title": "Die Antwoord",
                "town": "Будапешт",
                "price": {
                    "value": 5000
                }
            },
            {
                "id": 2,
                "title": "Socrat&Lera",
                "town": "Санкт-Петербург",
                "price": {
                    "value": 1999
                }
            },
            {
                "id": 3,
                "title": "Лампабикт",
                "town": "Москва",
                "price": {
                    "value": 2390
                }
            }
        ]
        """.trimIndent()

        val gson = Gson()
        val offerListType = object : TypeToken<List<Offer>>() {}.type
        return gson.fromJson(jsonString, offerListType)
    }

    fun tickets(): List<TicketsOffer> {
        val jsonString = """
        [
    {
      "id": 1,
      "title": "Уральские авиалинии",
      "time_range": [
        "07:00",
        "09:10",
        "10:00",
        "11:30",
        "14:15",
        "19:10",
        "21:00",
        "23:30"
      ],
      "price": {
        "value": 3999
      }
    },
    {
      "id": 10,
      "title": "Победа",
      "time_range": [
        "09:10",
        "21:00"
      ],
      "price": {
        "value": 4999
      }
    },
    {
      "id": 30,
      "title": "NordStar",
      "time_range": [
        "07:00"
      ],
      "price": {
        "value": 2390
      }
    }
  ]
        """.trimIndent()

        val gson = Gson()
        val offerListType = object : TypeToken<List<TicketsOffer>>() {}.type
        return gson.fromJson(jsonString, offerListType)
    }
}
