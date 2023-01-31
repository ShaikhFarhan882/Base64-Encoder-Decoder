package com.example.b64_encoder_decoder

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.b64_encoder_decoder.databinding.FragmentEncodeResultsBinding
import com.example.b64_encoder_decoder.viewmodels.MainViewModel
import es.dmoral.toasty.Toasty


class EncodeResults : Fragment() {


    private val args : EncodeResultsArgs by navArgs()
    private var _binding : FragmentEncodeResultsBinding? = null
    private val binding : FragmentEncodeResultsBinding
    get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentEncodeResultsBinding.inflate(layoutInflater)


        binding.apply {
            encodeResults.text = args.encodeddata.toString()
            copyText.setOnClickListener {
                copyData(encodeResults.text.toString())
                Toasty.success(requireContext(),"Copied Successfully",Toasty.LENGTH_SHORT).show()
            }

            backToHome.setOnClickListener {
                findNavController().navigate(R.id.action_encodeResults_to_home2)
            }
        }

        return binding.root
    }

    fun copyData(input : String){
        val copyText = input
        val clipboard = activity!!.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("copyText", copyText)
        clipboard.setPrimaryClip(clip)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}