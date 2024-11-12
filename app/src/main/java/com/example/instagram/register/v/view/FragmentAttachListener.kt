package com.example.instagram.register.v.view

interface FragmentAttachListener {
    fun goToNameAmdPasswordScreen(email:String)

    fun goToWelcomeScreen(name: String)

    fun goToPhotoScreen()
    fun goToMainScree()
    fun goToGaleryScreen()
    fun goToCameraScreen()
}