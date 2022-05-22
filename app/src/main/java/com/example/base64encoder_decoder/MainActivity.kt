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
import java.util.Base64


class MainActivity : AppCompatActivity() {

    val Clear : Button
    get() = findViewById(R.id.clear_text)

    val Encode : Button
    get() = findViewById(R.id.encode_text)

    val Decode : Button
    get() = findViewById(R.id.decode_text)

    val Copy : Button
    get() = findViewById(R.id.copy_text)

    val Input : EditText
    get() = findViewById(R.id.input_text)

    val Output : EditText
    get () = findViewById(R.id.output_text)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       Clear.setOnClickListener(View.OnClickListener {
           Input.setText("")
           Output.setText("")
           Toast.makeText(this,"Fields Cleared",Toast.LENGTH_SHORT).show()
       })

        Copy.setOnClickListener(View.OnClickListener {

            if(Output.text.isEmpty()){
                Toast.makeText(this,"Nothing to copy",Toast.LENGTH_SHORT).show()
            }

            else{
                val textToCopy = Output.text
                val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("text", textToCopy)
                clipboardManager.setPrimaryClip(clipData)
                Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_LONG).show()

            }

        })

       Encode.setOnClickListener(View.OnClickListener {

           if(Input.text.isEmpty()){
               Toast.makeText(this,"Please Enter Input",Toast.LENGTH_SHORT).show()
           }
           else{
               val input = input_text?.text.toString()
               val byte = input.toByteArray(charset("UTF-8"))
               val result = Base64.getEncoder().encodeToString(byte)

               Output.setText(result)
               Toast.makeText(this,"Successfully Encoded",Toast.LENGTH_SHORT).show()

           }


       })

        Decode.setOnClickListener(View.OnClickListener {

            if(Input.text.isEmpty()){
                Toast.makeText(this,"Please Enter Input",Toast.LENGTH_SHORT).show()
            }
            else{
                val input = input_text?.text.toString()
                val byte = Base64.getUrlDecoder().decode(input)
                val result = String(byte, charset("UTF-8"))

                Output.setText(result)
                Toast.makeText(this,"Sucessfully Decoded",Toast.LENGTH_SHORT).show()

            }

        })

    }
}