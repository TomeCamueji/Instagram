package com.example.instagram.register.v

import androidx.annotation.StringRes
import com.example.instagram.commom.base.BasePresenter
import com.example.instagram.commom.base.BaseView

interface RegisterNameAndPassword {

    interface presenter: BasePresenter {
        fun create(name: String,email: String,password: String, confirm: String)
    }
    interface view: BaseView<presenter> {
        fun showProgress(enabled:Boolean)
        fun displayNameFailure(@StringRes nameError: Int?)
        fun displaypasswordFailure(@StringRes passError: Int?)
        fun onCreateFailure(message: String)
        fun onCreateSucess(name: String)
    }
}