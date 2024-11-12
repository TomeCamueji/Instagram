package com.example.instagram.register.v.presentation

import com.example.instagram.R
import com.example.instagram.register.v.RegisterNameAndPassword
import com.example.instagram.register.v.data.RegisterCallback
import com.example.instagram.register.v.data.RegisterRepository

class RegisterNameAndPasswordPresenter (
    private var view: RegisterNameAndPassword.view?,
    private var repository:RegisterRepository

): RegisterNameAndPassword.presenter{
    override fun create(name: String, email: String, password: String, confirm: String) {
        val isNameValid = name.length >= 5
        val isPasswordValid = password.length >= 8
        val isConfirmValid = password == confirm

        if (!isNameValid){
            view?.displayNameFailure(R.string.invalid_name)
        }else{
            view?.displayNameFailure(null)
        }

        if (!isConfirmValid){
            view?.displaypasswordFailure(R.string.password_not_equal)
        }else{
            if (!isPasswordValid){
                view?.displaypasswordFailure(R.string.invalid_password)
            }else{
                view?.displaypasswordFailure(null)
            }
        }



        if (isNameValid && isPasswordValid && isConfirmValid){

            repository.create(email,name,password, object : RegisterCallback {
                override fun onSucess() {
                    view?.onCreateSucess(name)
                }

                override fun onFailure(message: String) {
                    view?.onCreateFailure(message)
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