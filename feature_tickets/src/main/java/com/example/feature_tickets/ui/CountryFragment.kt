package com.example.feature_tickets.ui

import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.feature_tickets.R
import com.example.feature_tickets.adapter.TicketAdapter
import com.example.feature_tickets.databinding.FragmentCountryBinding
import com.example.feature_tickets.viewmodel.TicketsViewModel
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Locale

class CountryFragment : Fragment() {

    private var _binding: FragmentCountryBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<TicketsViewModel>()
    private val calendar = Calendar.getInstance()

    private val constraints = CalendarConstraints.Builder()
        .setOpenAt(calendar.timeInMillis)
        .build()
    private val dateDialog = MaterialDatePicker.Builder.datePicker()
        .setCalendarConstraints(constraints)
        .build()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cross.setOnClickListener {
            binding.to.setText(getString(R.string.empty))
        }

        var isChange = false

        binding.upsidedown.setOnClickListener {
            isChange = !isChange

            if (isChange) {
                val temp = binding.from.text
                binding.from.text = binding.to.text
                binding.to.text = temp
            } else {
                val temp = binding.to.text
                binding.to.text = binding.from.text
                binding.from.text = temp
            }
        }

        val fromArg = arguments?.getString("fromArg")
        val toArg = arguments?.getString("toArg")

        binding.from.setText(fromArg)
        binding.to.setText(toArg)

        val adapter by lazy {
            TicketAdapter(requireContext())
        }

        binding.recycler.adapter = adapter

        viewModel.list.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        val dateFormat = SimpleDateFormat("dd MMM, E", Locale("ru"))
        val buttonText = dateFormat.format(calendar.time)
        val spannableString = SpannableString(buttonText)

        val dayOfWeekString = SimpleDateFormat("E", Locale("ru")).format(calendar.time)
        val startIndex = buttonText.indexOf(dayOfWeekString)

        if (startIndex != -1) {
            val color = Color.GRAY
            spannableString.setSpan(
                ForegroundColorSpan(color),
                startIndex,
                startIndex + dayOfWeekString.length,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        binding.date.text = spannableString

        binding.date.setOnClickListener {

            dateDialog.show(childFragmentManager, "datePicker")

            dateDialog.addOnPositiveButtonClickListener { timeMillis ->
                calendar.timeInMillis = timeMillis
                val buttonText2 = dateFormat.format(timeMillis)
                val spannableString2 = SpannableString(buttonText2)

                val dayOfWeekString2 = SimpleDateFormat("E", Locale("ru")).format(timeMillis)
                val startIndex2 = buttonText2.indexOf(dayOfWeekString2)

                if (startIndex2 != -1) {
                    val color = Color.GRAY
                    spannableString2.setSpan(
                        ForegroundColorSpan(color),
                        startIndex2,
                        startIndex2 + dayOfWeekString2.length,
                        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }

                binding.date.text = spannableString2
            }
        }
        binding.bigbtn.setOnClickListener {
            val bundle = Bundle()
            val date = calendar.timeInMillis
            val fromArg1 = binding.from.text.toString()
            val toArg1 = binding.to.text.toString()

            bundle.putString("fromArg", fromArg1)
            bundle.putString("toArg", toArg1)
            bundle.putLong("date", date)
            findNavController().navigate(R.id.lastFragment, bundle)
        }
        binding.back.setOnClickListener {

            dateDialog.show(childFragmentManager, "datePicker")

            dateDialog.addOnPositiveButtonClickListener { timeMillis ->
                val buttonText2 = dateFormat.format(timeMillis)
                val spannableString2 = SpannableString(buttonText2)

                val dayOfWeekString2 = SimpleDateFormat("E", Locale("ru")).format(timeMillis)
                val startIndex2 = buttonText2.indexOf(dayOfWeekString2)

                if (startIndex2 != -1) {
                    val color = Color.GRAY
                    spannableString2.setSpan(
                        ForegroundColorSpan(color),
                        startIndex2,
                        startIndex2 + dayOfWeekString2.length,
                        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }

                binding.back.text = spannableString2

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}