package com.example.instagram.register.v.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.databinding.FragmentRegisterPhotoBinding
import com.example.instagram.commom.util.CustomDialog

class RegisterPhotoFragment:Fragment(R.layout.fragment_register_photo) {
    private var bindng: FragmentRegisterPhotoBinding?=null

    private var fragmentAttachListener: FragmentAttachListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindng = FragmentRegisterPhotoBinding.bind(view)

        bindng?.let {
            with(it){
                registerTxtJump.setOnClickListener {
                    fragmentAttachListener?.goToMainScree()
                }

                registerBtnNext.isEnabled = true
                registerBtnNext.setOnClickListener {
                    OpenDialog()
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

    fun OpenDialog(){
        val customDialog = CustomDialog(requireContext())
        customDialog.addButton(R.string.photo,R.string.gallery){
            when(it.id){
                R.string.photo ->{
                   fragmentAttachListener?.goToCameraScreen()
                }
                R.string.gallery ->{
                    fragmentAttachListener?.goToGaleryScreen()
                }
            }
        }

        customDialog.show()
    }
    override fun onDestroy() {
        bindng = null
        super.onDestroy()
    }
}