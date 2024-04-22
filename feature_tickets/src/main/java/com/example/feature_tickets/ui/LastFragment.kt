package com.example.feature_tickets.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.feature_tickets.R
import com.example.feature_tickets.adapter.LastAdapter
import com.example.feature_tickets.databinding.FragmentLastBinding
import com.example.feature_tickets.viewmodel.LastTicketsVM
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Locale

class LastFragment : Fragment() {

    private var _binding: FragmentLastBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<LastTicketsVM>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fromArg = arguments?.getString("fromArg")
        val toArg = arguments?.getString("toArg")
        val date = arguments?.getLong("date")

        val dateFormat = SimpleDateFormat("dd MMMM", Locale("ru"))

        val dateString = dateFormat.format(date)

        binding.destination.text = getString(R.string.dest, fromArg, toArg)
        binding.date.text = getString(R.string._1, dateString)
        binding.arr.setOnClickListener {
            findNavController().popBackStack()
        }

        val adapter by lazy {
            LastAdapter(requireContext())
        }
        binding.recycler.adapter = adapter
        viewModel.list.observe(viewLifecycleOwner) {
            adapter.submitList(it)

        }
    }

}