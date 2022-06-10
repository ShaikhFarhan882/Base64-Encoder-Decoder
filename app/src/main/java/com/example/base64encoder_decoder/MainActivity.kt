package com.example.base64encoder_decoder

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.base64encoder_decoder.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.UnsupportedEncodingException
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.clearText.setOnClickListener(View.OnClickListener {
            GlobalScope.launch (Dispatchers.Main){
                clearFields()
            }

        })

        binding.copyText.setOnClickListener(View.OnClickListener {

         val copyText = binding.outputText.text.toString()
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("copyText", copyText)
            clipboard.setPrimaryClip(clip)
        })

        binding.encodeText.setOnClickListener(View.OnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                encodeText()
            }

        })

        binding.decodeText.setOnClickListener(View.OnClickListener {

            GlobalScope.launch(Dispatchers.Main) {
                decodeText()
            }

        })

    }

    suspend fun decodeText() {
        if (binding.inputText.text.isEmpty()) {
            Toast.makeText(this, "Please Enter Input", Toast.LENGTH_SHORT).show()
        }
        try {
            val input = input_text?.text.toString()
            val byte = Base64.getUrlDecoder().decode(input)
            val result = String(byte, charset("UTF-8"))

            binding.outputText.setText(result)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (ar: IllegalArgumentException) {
            ar.printStackTrace()
            Toast.makeText(this, "Unsupported Text", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun encodeText() {
        if (binding.inputText.text.isEmpty()) {
            Toast.makeText(this, "Please Enter Input", Toast.LENGTH_SHORT).show()
        }
        try {
            val input = input_text?.text.toString()
            val byte = input.toByteArray(charset("UTF-8"))
            val result = Base64.getUrlEncoder().encodeToString(byte)

            binding.outputText.setText(result)

        } catch (e: UnsupportedEncodingException) {
            Toast.makeText(this, "Unsupported Text", Toast.LENGTH_SHORT).show()

        }
    }

    suspend fun clearFields() {
        if (binding.inputText.text.isNotEmpty()){
            binding.inputText.setText("")
            binding.outputText.setText("")
            Toast.makeText(this, "Fields Cleared", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Nothing to Clear", Toast.LENGTH_SHORT).show()
        }
    }


}