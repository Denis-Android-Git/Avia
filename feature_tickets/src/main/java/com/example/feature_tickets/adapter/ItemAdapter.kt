package com.example.feature_tickets.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import com.example.feature_tickets.R
import com.example.feature_tickets.databinding.ItemPagerBinding
import com.example.feature_tickets.domain.model.Offer
import com.example.delegates.ListDelegateAdapter
import com.example.delegates.adapterDelegate
import java.text.NumberFormat
import java.util.Locale

class ItemAdapter(
    context: Context
) : ListDelegateAdapter<Offer>(ItemDiffUtil()) {
    init {
        addDelegate(itemDelegate(context))
    }
}

@SuppressLint("StringFormatMatches")
fun itemDelegate(
    context: Context
) = adapterDelegate<Offer, Offer, ItemPagerBinding>({
    ItemPagerBinding.inflate(
        LayoutInflater.from(it.context), it, false
    )
}) {
    bind {
        when (item.id) {
            1 -> binding.image.setImageResource(R.drawable.one)
            2 -> binding.image.setImageResource(R.drawable.two)
            3 -> binding.image.setImageResource(R.drawable.three)
        }
        binding.title.text = item.title
        binding.city.text = item.town
        val formattedVal =
            NumberFormat.getNumberInstance(Locale.getDefault()).format(item.price.value)
        binding.price.text = context.getString(R.string.price, formattedVal)
    }
}

class ItemDiffUtil : DiffUtil.ItemCallback<Offer>() {
    override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean =
        oldItem.id == newItem.id
}