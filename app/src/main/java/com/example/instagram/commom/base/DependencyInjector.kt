package com.example.instagram.commom.base

import com.example.instagram.login.data.FakeDataSource
import com.example.instagram.login.data.LoginRepository
import com.example.instagram.register.v.data.FakeRegisterDataSource
import com.example.instagram.register.v.data.RegisterRepository

object DependencyInjector {
    fun loginRepository(): LoginRepository{
        return LoginRepository(FakeDataSource())
    }

    fun RegisterEmailRepository(): RegisterRepository{
        return RegisterRepository(FakeRegisterDataSource())
    }
}