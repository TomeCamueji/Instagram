package com.example.instagram.commom.base

import com.example.instagram.login.Login

interface BaseView<T> {
    var presenter: T
}