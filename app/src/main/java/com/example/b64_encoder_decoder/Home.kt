package com.example.b64_encoder_decoder

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.b64_encoder_decoder.databinding.FragmentHomeBinding
import com.example.b64_encoder_decoder.viewmodels.MainViewModel
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class Home : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this

        binding.apply {
            encodeText.setOnClickListener {

                if (inputText.text.toString().isNotEmpty()) {
                    lifecycleScope.launch{
                        val input = inputText.text.toString()
                        val data = viewModel.encodeURL(input)
                        val action = HomeDirections.actionHome2ToEncodeResults(data)
                        findNavController().navigate(action)
                        inputText.text.clear()
                    }
                } else {
                    Toasty.error(requireContext(), "No Input Provided", Toasty.LENGTH_SHORT).show()
                }

            }

            decodeText.setOnClickListener {
                val isBase64: Boolean = checkForEncode(inputText.text.toString())

                if (!TextUtils.isEmpty(inputText.text.toString()) && isBase64) {
                    lifecycleScope.launch{
                        val input = inputText.text.toString()
                        val data = viewModel.decode(input)
                        val action = HomeDirections.actionHome2ToDecodeResults(data)
                        findNavController().navigate(action)
                        inputText.text.clear()
                    }

                } else if (TextUtils.isEmpty(inputText.text.toString())) {
                    Toasty.error(requireContext(), "No Input Provided", Toasty.LENGTH_SHORT).show()
                } else {
                    Toasty.error(requireContext(),
                        "Base64 format is required for decoding",
                        Toasty.LENGTH_SHORT).show()
                }
            }

            clearText.setOnClickListener {
                inputText.text.clear()
            }
        }

        return binding.root
    }


    private fun checkForEncode(string: String?): Boolean {
        val pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$"
        val r: Pattern = Pattern.compile(pattern)
        val m: Matcher = r.matcher(string)
        if (m.find()) {
            return true
        }
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}