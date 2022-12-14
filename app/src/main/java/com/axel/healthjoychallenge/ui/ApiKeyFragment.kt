package com.axel.healthjoychallenge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.axel.healthjoychallenge.R
import com.axel.healthjoychallenge.databinding.FragmentApiKeyBinding
import com.axel.healthjoychallenge.ui.home.HomeFragment.Companion.API_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApiKeyFragment : Fragment() {


    private var _binding: FragmentApiKeyBinding? = null
    private val binding: FragmentApiKeyBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentApiKeyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBtnProceed()

    }

    private fun setupBtnProceed() {
        binding.btnProceed.setOnClickListener {
            val apikey = binding.etApiKey.text.toString()
            if (apikey.isNotEmpty()) {
                findNavController().navigate(R.id.action_apiKeyFragment_to_homeFragment, bundleOf(API_KEY to apikey))
            } else {
                Toast.makeText(context, R.string.place_your_api_key, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}