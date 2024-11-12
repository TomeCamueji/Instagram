package com.example.instagram.register.v.data

import android.os.Looper
import com.example.instagram.commom.model.Database
import com.example.instagram.commom.model.UserAuth
import java.util.UUID

class FakeRegisterDataSource: RegisterDataSource {
    override fun create(email: String, callback: RegisterCallback) {
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            val userAuth =  Database.usersAuth .firstOrNull{ email == it.email }
           if (userAuth == null){
               callback.onSucess()
           }else{
               callback.onFailure("Usuário já cadastrado")
           }
            callback.onComplete()
        },200)
    }

    override fun create(name: String, email: String, password: String, callback: RegisterCallback) {
        android.os.Handler(Looper.getMainLooper()).postDelayed({

            val userAuth =  Database.usersAuth .firstOrNull{ email == it.email && name == it.name }

            if (userAuth != null){
                callback.onFailure("Usuário já cadastrado")
            }else{
                val newUser = UserAuth(UUID.randomUUID().toString(),name,email,password)

                val created = Database.usersAuth.add(newUser)

                if (created){
                    Database.sessionAuth = newUser
                    callback.onSucess()
                }else{
                    callback.onFailure("Erro no servidor.")
                }
            }
            callback.onComplete()
        },200)
    }
}