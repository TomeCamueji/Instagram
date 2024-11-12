package com.example.instagram.login.data

import android.os.Looper
import com.example.instagram.commom.model.Database

class FakeDataSource: LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallback) {
        android.os.Handler(Looper.getMainLooper()).postDelayed({
       val userAuth =  Database.usersAuth .firstOrNull{ email == it.email }
            if (userAuth == null){
                callback.onFailure("Usuário não encontrado")
            }else if (userAuth.password != password){
                callback.onFailure("Senha está incorreta")
            }else{
                callback.onSucess(userAuth)
            }
            callback.onComplete()
        },200)
    }
}