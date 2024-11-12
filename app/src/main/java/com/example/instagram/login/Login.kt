package com.example.instagram.login

import androidx.annotation.StringRes
import com.example.instagram.commom.base.BasePresenter
import com.example.instagram.commom.base.BaseView

interface Login {

    interface presenter : BasePresenter{
        fun login(email:String,password:String)

    }
    interface view: BaseView<presenter>{
        fun showProgress()
        fun displayEmailFailure(@StringRes emailError:Int?)
        fun displasyPasswordFailure(@StringRes passwordError:Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized(message:String)
    }
}