package com.example.b64_encoder_decoder.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.io.UnsupportedEncodingException
import java.util.*

class MainViewModel : ViewModel() {

    /*val encodedData: MutableLiveData<String> = MutableLiveData()*/


   /* fun encode(input: String): String {
        var encodedString: String = ""
        try {
            val byte = input.toByteArray(charset("UTF-8"))
            encodedString = Base64.getUrlEncoder().encodeToString(byte)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return encodedString
    }*/

    suspend fun decode(input: String): String {
        var decodeString: String = ""
        withContext(Dispatchers.IO){
            try {
                val byte = Base64.getUrlDecoder().decode(input)
                decodeString = String(byte, charset("UTF-8"))
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            } catch (ar: IllegalArgumentException) {
                ar.printStackTrace()
            }
        }
        return decodeString
    }


    suspend fun encodeURL(input: String) : String {
        var encodedString : String = ""
        withContext(Dispatchers.IO){
            try {
                val byte = input.toByteArray(charset("UTF-8"))
                encodedString = Base64.getUrlEncoder().encodeToString(byte)
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
        }
        return encodedString
    }
}

