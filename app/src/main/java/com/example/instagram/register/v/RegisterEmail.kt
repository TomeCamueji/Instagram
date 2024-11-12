package com.example.instagram.register.v

import androidx.annotation.StringRes
import com.example.instagram.commom.base.BasePresenter
import com.example.instagram.commom.base.BaseView

interface RegisterEmail {

    interface presenter: BasePresenter{
        fun create(email: String)
    }
    interface view: BaseView<presenter>{
        fun showProgress(enabled:Boolean)
        fun displayEmailFailure(@StringRes emailError: Int?)
        fun onEmailFailure(message: String)
        fun gotoNameAndPasswordSrceen(email: String)
    }
}