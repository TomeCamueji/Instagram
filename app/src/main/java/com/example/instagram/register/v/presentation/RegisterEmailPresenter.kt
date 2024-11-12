package com.example.instagram.register.v.presentation

import android.util.Patterns
import com.example.instagram.R
import com.example.instagram.register.v.RegisterEmail
import com.example.instagram.register.v.data.RegisterCallback
import com.example.instagram.register.v.data.RegisterRepository

class RegisterEmailPresenter (
    private var view: RegisterEmail.view?,
    private var repository:RegisterRepository

): RegisterEmail.presenter{
    override fun create(email: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        if (!isEmailValid){
            view?.displayEmailFailure(R.string.invalid_email)
        }else{
            view?.displayEmailFailure(null)
        }

        if (isEmailValid){

            repository.create(email, object : RegisterCallback {
                override fun onSucess() {
                    view?.gotoNameAndPasswordSrceen(email)

                }

                override fun onFailure(message: String) {
                    view?.onEmailFailure(message)

                }

                override fun onComplete() {
                    view?.showProgress(false)

                }


            })
        }

    }

    override fun onDestroy() {
        view = null
    }

}