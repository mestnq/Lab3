package com.example.lab3.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.R
import com.example.lab3.databinding.BigCardBinding
import com.example.lab3.databinding.BigCardWithoutBagBinding
import com.example.lab3.databinding.MiddleCardBinding
import com.example.lab3.databinding.SmallCardBinding
import com.example.lab3.models.Card
import com.squareup.picasso.Picasso

class CardsListAdapter : ListAdapter<Card, RecyclerView.ViewHolder>(MyDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> {
                val binding = BigCardWithoutBagBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                DefaultCardWithBackgroundHolder(binding)
            }
            R.layout.big_card_without_bag -> {
                val binding = BigCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                DefaultCardHolder(binding)
            }
            R.layout.small_card -> {
                val binding = SmallCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                CardWithoutImageHolder(binding)
            }
            R.layout.middle_card -> {
                val binding = MiddleCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                RoundCardHolder(binding)
            }
            else -> throw IllegalStateException("Unknown view type $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Card.DefaultCardBackground -> 0
            is Card.DefaultCard -> R.layout.big_card_without_bag
            is Card.CardWithoutImage -> R.layout.small_card
            is Card.RoundCard -> R.layout.middle_card
            else -> Int.MAX_VALUE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> (holder as DefaultCardWithBackgroundHolder).bind(getItem(position) as Card.DefaultCardBackground)
            R.layout.big_card_without_bag -> (holder as DefaultCardHolder).bind(getItem(position) as Card.DefaultCard)
            R.layout.small_card -> (holder as CardWithoutImageHolder).bind(getItem(position) as Card.CardWithoutImage)
            R.layout.middle_card -> (holder as RoundCardHolder).bind(getItem(position) as Card.RoundCard)
            else -> throw IllegalStateException("Unknown item view type ${holder.itemViewType}")
        }
    }

    inner class DefaultCardHolder(private val binding: BigCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card.DefaultCard) = with(binding) {
            try {
                Picasso.get().load(card.img)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.broken_image)
                    .into(background)
            } catch (ex: Exception) {
                Log.e("Error", ex.message.toString())
                ex.printStackTrace()
            }
            header.text = card.title
            text.text = card.subtitle
        }
    }

    inner class DefaultCardWithBackgroundHolder(private val binding: BigCardWithoutBagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card.DefaultCardBackground) = with(binding) {
            try {
                Picasso.get().load(card.img)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.broken_image)
                    .into(background)
            } catch (ex: Exception) {
                Log.e("Error", ex.message.toString())
                ex.printStackTrace()
            }
            header.text = card.title
            text.text = card.subtitle
            try {
                val color = Color.parseColor(card.hasBag)
            } catch (ex: Exception) {
                Log.e("Error", ex.message.toString())
                ex.printStackTrace()
            }
        }
    }

    inner class CardWithoutImageHolder(private val binding: SmallCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card.CardWithoutImage) = with(binding) {
            header.text = card.title
            subheader.text = card.subtitle
        }
    }

    inner class RoundCardHolder(private val binding: MiddleCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card.RoundCard) = with(binding) {
            header.text = card.title
            subheader.text = card.subtitle
            if (card.isCircle) {
                try {
                    Picasso.get().load(card.img)
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.broken_image)
                        .into(picture)
                } catch (ex: Exception) {
                    Log.e("Error", ex.message.toString())
                    ex.printStackTrace()
                }
            }
        }
    }

    class MyDiffCallback : DiffUtil.ItemCallback<Card>() {

        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }
    }
}