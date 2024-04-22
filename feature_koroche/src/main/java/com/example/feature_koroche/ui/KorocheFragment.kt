package com.example.feature_koroche.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.feature_koroche.databinding.FragmentKorocheBinding
import com.example.feature_koroche.viewmodel.KorocheViewModel

class KorocheFragment : Fragment() {

    private var _binding: FragmentKorocheBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val korocheViewModel =
            ViewModelProvider(this).get(KorocheViewModel::class.java)

        _binding = FragmentKorocheBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        korocheViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}