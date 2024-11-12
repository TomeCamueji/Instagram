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
import com.example.instagram.register.v.RegisterNameAndPassword
import com.example.instagram.register.v.presentation.RegisterNameAndPasswordPresenter

class RegisterNamePasswordFragment:Fragment(R.layout.fragment_register_name_password),RegisterNameAndPassword.view {
    private  var binding: FragmentRegisterNamePasswordBinding? =null
    private var fragmentAttachListener : FragmentAttachListener? =null

    override lateinit var presenter: RegisterNameAndPassword.presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterNamePasswordBinding.bind(view)

        val repository = DependencyInjector.RegisterEmailRepository()
        presenter = RegisterNameAndPasswordPresenter(this,repository)

        val email = arguments?.getString(KEY_EMAIL) ?:throw IllegalArgumentException("email not found")
        Log.i("teste",email.toString())

        binding?.let {
            with(it){
                registerTxtLogin.setOnClickListener {
                    activity?.finish()
                }
                registerNameBtnNext.setOnClickListener {
                presenter.create(
                    email,
                    registerEditName.text.toString(),
                    registerEditPassword.text.toString(),
                    registerEditConfirm.text.toString()
                )
                }

                registerEditName.addTextChangedListener(watcher)
                registerEditPassword.addTextChangedListener(watcher)
                registerEditConfirm.addTextChangedListener(watcher)

                registerEditName.addTextChangedListener(TxtWatcher{
                    displayNameFailure(null)
                })
                registerEditPassword.addTextChangedListener(TxtWatcher{
                    displaypasswordFailure(null)
                })
                registerEditConfirm.addTextChangedListener(TxtWatcher{
                    displaypasswordFailure(null)
                })

            }
        }
    }


    override fun showProgress(enabled: Boolean) {

    }

    override fun displayNameFailure(nameError: Int?) {
        binding?.registerNameInput?.error = nameError?.let {getString(it)}
    }

    override fun displaypasswordFailure(passError: Int?) {
      binding?.registerPasswordInput?.error = passError?.let { getString(it) }
    }

    override fun onCreateFailure(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }

    override fun onCreateSucess(name: String) {
       fragmentAttachListener?.goToWelcomeScreen(name)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }

    private val watcher = TxtWatcher {

        binding?.registerNameBtnNext?.isEnabled = binding?.registerEditName?.text.toString().isNotEmpty()
                && binding?.registerEditPassword?.text.toString().isNotEmpty()
                && binding?.registerEditConfirm?.text.toString().isNotEmpty()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        binding = null
    }
    companion object {
        const val KEY_EMAIL = "key_email"
    }

}