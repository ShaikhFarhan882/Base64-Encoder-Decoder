package com.example.base64encoder_decoder

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import android.view.View
import android.widget.Button
import android.widget.EdgeEffect
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.UnsupportedEncodingException
import java.util.Base64


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        clear_text.setOnClickListener(View.OnClickListener {
            input_text.setText("")
            output_text.setText("")
            Toast.makeText(this, "Fields Cleared", Toast.LENGTH_SHORT).show()
        })

        copy_text.setOnClickListener(View.OnClickListener {

            if (output_text.text.isEmpty()) {
                Toast.makeText(this, "Nothing to copy", Toast.LENGTH_SHORT).show()
            } else {
                val textToCopy = output_text.text
                val clipboardManager =
                    getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("text", textToCopy)
                clipboardManager.setPrimaryClip(clipData)
                Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_LONG).show()

            }

        })

        encode_text.setOnClickListener(View.OnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                encodeText()
            }

        })

        decode_text.setOnClickListener(View.OnClickListener {

            GlobalScope.launch(Dispatchers.Main) {
                decodeText()
            }

        })

    }

    suspend fun decodeText() {
        if (input_text.text.isEmpty()) {
            Toast.makeText(this, "Please Enter Input", Toast.LENGTH_SHORT).show()
        }
        try {
            val input = input_text?.text.toString()
            val byte = Base64.getUrlDecoder().decode(input)
            val result = String(byte, charset("UTF-8"))


            output_text.setText(result)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (ar: IllegalArgumentException) {
            ar.printStackTrace()
            Toast.makeText(this, "Unsupported Text", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun encodeText() {
        if (input_text.text.isEmpty()) {
            Toast.makeText(this, "Please Enter Input", Toast.LENGTH_SHORT).show()
        }
        try {
            val input = input_text?.text.toString()
            val byte = input.toByteArray(charset("UTF-8"))
            val result = Base64.getUrlEncoder().encodeToString(byte)

            output_text.setText(result)

        } catch (e: UnsupportedEncodingException) {
            Toast.makeText(this, "Unsupported Text", Toast.LENGTH_SHORT).show()

        }
    }

}