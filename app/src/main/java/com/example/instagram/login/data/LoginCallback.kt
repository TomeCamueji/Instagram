package com.example.instagram.login.data

import com.example.instagram.commom.model.UserAuth

interface LoginCallback {
    fun onSucess(userAuth: UserAuth)
    fun onFailure(message:String)
    fun onComplete()
}
