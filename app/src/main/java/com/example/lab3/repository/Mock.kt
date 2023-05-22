package com.example.lab3.repository

import com.example.lab3.models.Card

class Mock {
    fun getData(): List<Card> {
        return getTools()
    }

    private fun getTools(): List<Card> {
        return listOf(
            Card.CardWithoutImage(
                "Header",
                "Subheader"
            ),
            Card.DefaultCard(
                "Header",
                "Subheader",
                "res\\drawable\\background_image.png"
            ),
            Card.RoundCard(
                "Extremely cool header",
                "Super cool text informative wow wow even more education information look at this picture",
                "res\\drawable\\background_image.png",
                true
            )
        )
    }
}