package com.example.instagram.register.v.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.commom.base.DependencyInjector
import com.example.instagram.commom.view.TxtWatcher
import com.example.instagram.databinding.FragmentRegisterNamePasswordBinding
import com.example.instagram.databinding.FragmentRegisterWelcomeBinding
import com.example.instagram.register.v.RegisterNameAndPassword
import com.example.instagram.register.v.presentation.RegisterNameAndPasswordPresenter

class RegisterWelcomeFragment:Fragment(R.layout.fragment_register_welcome) {
    private  var binding: FragmentRegisterWelcomeBinding? =null
    private var fragmentAttachListener : FragmentAttachListener? =null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterWelcomeBinding.bind(view)

        val name = arguments?.getString(KEY_NAME) ?:throw IllegalArgumentException("name not found")


        binding?.let {
            with(it){
                registerTxtWelcome.text = getString(R.string.welcome_to_instagram, name)

                registerBtnNext.isEnabled = true
            registerBtnNext.setOnClickListener {
                fragmentAttachListener?.goToPhotoScreen()
            }
            }
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    companion object {
        const val KEY_NAME = "key_name"
    }
}