package com.example.lab3.models

sealed class Card (
    open val title: String = "",
    open val subtitle: String = ""
) {
    data class DefaultCard (
        override val title: String = "",
        override val subtitle: String = "",
        val img: String = ""
    ) : Card(title, subtitle)

    data class DefaultCardBackground(
        override val title: String = "",
        override val subtitle: String = "",
        val img: String = "",
        val hasBag: String = "#000000"
    ) : Card(title, subtitle)

    data class CardWithoutImage(
        override val title: String = "",
        override val subtitle: String = "",
    ) : Card(title, subtitle)

    data class RoundCard(
        override val title: String = "",
        override val subtitle: String = "",
        val img: String = "",
        val isCircle: Boolean = false
    ) : Card(title, subtitle)
    data class ErrorCard(
        override val title: String = "error",
        override val subtitle: String = "information about error",
    ): Card(title, subtitle)
    companion object Factory {
        private fun getTool(res: CardRequest): Card {
            return if (res.img == null) {
                CardWithoutImage(res.title, res.subtitle)
            } else if (res.isCircle != null) {
                RoundCard(res.title, res.subtitle, res.img, res.isCircle)
            } else if (res.hasBag != null) {
                DefaultCardBackground(res.title, res.subtitle, res.img, res.hasBag)
            } else {
                DefaultCard(res.title, res.subtitle, res.img)
            }
        }

        fun getTools(responses: List<CardRequest>): List<Card> {
            return responses.map { getTool(it) }
        }
    }
}