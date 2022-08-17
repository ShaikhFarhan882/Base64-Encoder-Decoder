package com.example.b64_encoder_decoder.viewmodels

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.io.UnsupportedEncodingException
import java.util.*

class MainViewModel : ViewModel() {


     suspend fun encode(input: String) : String{
        var result : String = ""
        try {
            val byte = input.toByteArray(charset("UTF-8"))
            result = Base64.getUrlEncoder().encodeToString(byte)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return result
    }
    suspend fun decode(input: String) : String {

        var result : String = ""
        try {
            val byte = Base64.getUrlDecoder().decode(input)
            result = String(byte, charset("UTF-8"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (ar: IllegalArgumentException) {
            ar.printStackTrace()
        }
        return result
    }


}