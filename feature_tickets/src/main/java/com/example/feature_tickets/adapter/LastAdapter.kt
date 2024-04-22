package com.example.feature_tickets.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import com.example.feature_tickets.R
import com.example.feature_tickets.databinding.ItemLastBinding
import com.example.feature_tickets.domain.model.Ticket
import com.example.feature_tickets.utils.calculateFlightTime
import ru.sr.adapter.ListDelegateAdapter
import ru.sr.adapter.adapterDelegate
import java.text.NumberFormat
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
class LastAdapter(
    context: Context
) : ListDelegateAdapter<Ticket>(LastDiffUtil()) {
    init {
        addDelegate(lastDelegate(context))
    }
}

@RequiresApi(Build.VERSION_CODES.O)
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
        val departure = item.departure.date.split("T")[1].removeSuffix(":00")
        val arrival = item.arrival.date.split("T")[1].removeSuffix(":00")

        binding.time.text =
            context.getString(R.string.arrival, departure, arrival)
        binding.code1.text = item.departure.airport
        binding.code2.text = item.arrival.airport
        binding.transfer.text =
            context.getString(
                R.string.triptime,
                calculateFlightTime(item.departure.date, item.arrival.date).toString()
            )
        if (item.has_transfer) {
            binding.noTransfer.text = ""
        }
    }
}

class LastDiffUtil : DiffUtil.ItemCallback<Ticket>() {
    override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean =
        oldItem.id == newItem.id
}