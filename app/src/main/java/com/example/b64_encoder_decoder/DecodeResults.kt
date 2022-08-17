package com.example.b64_encoder_decoder

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.b64_encoder_decoder.databinding.FragmentDecodeResultsBinding
import es.dmoral.toasty.Toasty

class DecodeResults : Fragment() {
    private val args : DecodeResultsArgs by navArgs()
    private var _binding : FragmentDecodeResultsBinding? = null
    private val binding : FragmentDecodeResultsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDecodeResultsBinding.inflate(layoutInflater)

        binding.apply {
            decodeResults.text = args.decodeddata.toString()

            copyText.setOnClickListener {
                copyData(decodeResults.text.toString())
                Toasty.success(requireContext(),"Copied Successfully", Toasty.LENGTH_SHORT).show()
            }


            backToHome.setOnClickListener {
                findNavController().navigate(R.id.action_decodeResults_to_home2)
            }
        }

        return binding.root
    }



    fun copyData(input : String){
        val copyText = input
        val clipboard = activity!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("copyText", copyText)
        clipboard.setPrimaryClip(clip)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}