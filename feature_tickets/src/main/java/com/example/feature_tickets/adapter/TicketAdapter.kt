package com.example.feature_tickets.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import com.example.feature_tickets.R
import com.example.feature_tickets.databinding.ItemOfferBinding
import com.example.feature_tickets.domain.model.TicketsOffer
import com.example.delegates.ListDelegateAdapter
import com.example.delegates.adapterDelegate
import java.text.NumberFormat
import java.util.Locale

class TicketAdapter(
    context: Context
) : ListDelegateAdapter<TicketsOffer>(TicketDiffUtil()) {
    init {
        addDelegate(ticketDelegate(context))
    }
}

@SuppressLint("StringFormatMatches")
fun ticketDelegate(
    context: Context
) = adapterDelegate<TicketsOffer, TicketsOffer, ItemOfferBinding>({
    ItemOfferBinding.inflate(
        LayoutInflater.from(it.context), it, false
    )
}) {
    bind {
        when (item.id) {
            1 -> binding.image.setImageResource(R.drawable.red)
            10 -> binding.image.setImageResource(R.drawable.blue)
            30 -> binding.image.setImageResource(R.drawable.white)
        }
        binding.company.text = item.title
        val formattedVal =
            NumberFormat.getNumberInstance(Locale.getDefault()).format(item.price.value)
        binding.price.text = context.getString(R.string.price, formattedVal)
        binding.time.text = item.time_range.joinToString(" ")
    }
}

class TicketDiffUtil : DiffUtil.ItemCallback<TicketsOffer>() {
    override fun areItemsTheSame(oldItem: TicketsOffer, newItem: TicketsOffer): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: TicketsOffer, newItem: TicketsOffer): Boolean =
        oldItem.id == newItem.id
}