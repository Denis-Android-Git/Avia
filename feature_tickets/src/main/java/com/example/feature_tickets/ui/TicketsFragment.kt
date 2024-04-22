package com.example.feature_tickets.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.feature_tickets.R
import com.example.feature_tickets.adapter.ItemAdapter
import com.example.feature_tickets.databinding.FragmentTicketsBinding
import com.example.feature_tickets.viewmodel.OfferViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TicketsFragment : Fragment() {

    private var _binding: FragmentTicketsBinding? = null

    private val binding get() = _binding!!

    private val offerViewModel by viewModel<OfferViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scope = CoroutineScope(Dispatchers.Main)

        val bottomSheetBehavior: BottomSheetBehavior<View> =
            BottomSheetBehavior.from<View>(binding.sheetBottom).apply {
                state = BottomSheetBehavior.STATE_HIDDEN
                peekHeight = 0
            }

        binding.to.isFocusable = false
        binding.to.clearFocus()
        binding.to.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            binding.from2.text = binding.from.text
        }

        val prefs = requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE)

        val regex = Regex("[а-яё]+", RegexOption.IGNORE_CASE)

        val adapter by lazy {
            ItemAdapter(requireContext())
        }
        binding.viewpager.adapter = adapter

        val from = prefs.getString("from", "")

        binding.from.setText(from)

        binding.from.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = s.toString()
                if (input.isNotEmpty() && !input.matches(regex)) {
                    binding.from.error = getString(R.string.only_russian)
                } else {
                    val editor = prefs.edit()
                    editor.putString("from", input)
                    editor.apply()
                    binding.from.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.to.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = s.toString()
                if (input.isNotEmpty() && !input.matches(regex)) {
                    binding.to.error = getString(R.string.only_russian)
                } else {
                    binding.to.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.to2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()
                if (input.isNotEmpty()) {
                    scope.launch {
                        val bundle = Bundle()
                        val fromArg = binding.from2.text.toString()
                        val toArg = binding.to2.text.toString()

                        bundle.putString("fromArg", fromArg)
                        bundle.putString("toArg", toArg)

                        delay(400)
                        findNavController().navigate(R.id.countryFragment, bundle)
                        binding.to2.setText(getString(R.string.empty))
                    }
                }
            }
        })

        offerViewModel.list.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        binding.route.setOnClickListener {
            findNavController().navigate(R.id.blankFragment)
        }
        binding.weekend.setOnClickListener {
            findNavController().navigate(R.id.blankFragment)
        }
        binding.hot.setOnClickListener {
            findNavController().navigate(R.id.blankFragment)
        }
        binding.globe.setOnClickListener {
            binding.to2.setText(getString(R.string.enywhere))
        }
        binding.stambul.setOnClickListener {
            binding.to2.setText(getString(R.string.stambul))
        }
        binding.sochi.setOnClickListener {
            binding.to2.setText(getString(R.string.sochi))
        }
        binding.phuket.setOnClickListener {
            binding.to2.setText(getString(R.string.puket))
        }
        binding.cross.setOnClickListener {
            binding.to2.setText(getString(R.string.empty))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}