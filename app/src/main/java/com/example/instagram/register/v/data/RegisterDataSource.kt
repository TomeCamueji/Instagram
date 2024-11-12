package com.example.instagram.register.v.data

interface RegisterDataSource {

    fun create(email:String, callback: RegisterCallback)
    fun create(name: String, email:String, password: String, callback: RegisterCallback)
}