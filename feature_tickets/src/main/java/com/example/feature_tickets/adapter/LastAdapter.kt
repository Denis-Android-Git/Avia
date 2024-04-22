package com.example.feature_tickets.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.example.feature_tickets.R
import com.example.feature_tickets.databinding.ItemLastBinding
import com.example.feature_tickets.domain.model.Ticket
import ru.sr.adapter.ListDelegateAdapter
import ru.sr.adapter.adapterDelegate
import java.text.NumberFormat
import java.util.Locale

class LastAdapter(
    context: Context
) : ListDelegateAdapter<Ticket>(LastDiffUtil()) {
    init {
        addDelegate(lastDelegate(context))
    }
}

@SuppressLint("StringFormatMatches")
fun lastDelegate(
    context: Context
) = adapterDelegate<Ticket, Ticket, ItemLastBinding>({
    ItemLastBinding.inflate(
        LayoutInflater.from(it.context), it, false
    )
}) {
    bind {
        val formattedVal =
            NumberFormat.getNumberInstance(Locale.getDefault()).format(item.price.value)
        binding.price.text = context.getString(R.string.price, formattedVal)
        if (item.badge == null) {
            binding.badge.visibility = View.GONE
        } else {
            binding.badgeText.text = item.badge
        }
        binding.time.text =
            context.getString(R.string.arrival, item.departure.date, item.arrival.date)
        binding.code1.text = item.departure.airport
        binding.code2.text = item.arrival.airport
    }
}

class LastDiffUtil : DiffUtil.ItemCallback<Ticket>() {
    override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean =
        oldItem.id == newItem.id
}