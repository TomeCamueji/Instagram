package com.example.instagram.login.presentation

import android.util.Patterns
import com.example.instagram.R
import com.example.instagram.commom.model.UserAuth
import com.example.instagram.login.Login
import com.example.instagram.login.data.LoginCallback
import com.example.instagram.login.data.LoginRepository
import java.util.regex.Pattern

class LoginPresenter(
    private var view: Login.view?,
    private val repository: LoginRepository
): Login.presenter {
    override fun login(email: String, password: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >= 8
        if (!isEmailValid){
            view?.displayEmailFailure(R.string.invalid_email)
        }else{
            view?.displayEmailFailure(null)
        }
        if (!isPasswordValid){
            view?.displasyPasswordFailure(R.string.invalid_password)
        }else{
            view?.displasyPasswordFailure(null)
        }

        if (isEmailValid && isPasswordValid){

            repository.login(email,password, object : LoginCallback{
                override fun onSucess(userAuth: UserAuth) {
                    view?.onUserAuthenticated()
                }

                override fun onFailure(message: String) {
                    view?.onUserUnauthorized(message)
                }

                override fun onComplete() {
                    view?.showProgress()
                }

            })
        }
    }
    override fun onDestroy() {
       view = null
    }
}