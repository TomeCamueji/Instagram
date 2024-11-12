package com.example.instagram.register.v.data

interface RegisterCallback {
    fun onSucess()
    fun onFailure(message:String)
    fun onComplete()
}