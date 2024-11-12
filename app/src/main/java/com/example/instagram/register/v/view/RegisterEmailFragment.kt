package com.example.instagram.register.v.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.commom.base.DependencyInjector
import com.example.instagram.commom.view.TxtWatcher
import com.example.instagram.databinding.FragmentRegisterEmailBinding
import com.example.instagram.register.v.RegisterEmail
import com.example.instagram.register.v.presentation.RegisterEmailPresenter

class RegisterEmailFragment : Fragment(R.layout.fragment_register_email), RegisterEmail.view {

    private var binding: FragmentRegisterEmailBinding? = null
    private var fragmentAttachListener : FragmentAttachListener? =null

    override lateinit var presenter: RegisterEmail.presenter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterEmailBinding.bind(view)

        val repository = DependencyInjector.RegisterEmailRepository()
        presenter = RegisterEmailPresenter(this, repository)
//        with(binding){
//           binding!!.registerTxtLogin.setOnClickListener {
//                activity?.finish()
//            }
//        }

        binding?.let {
            with(it) {
                registerTxtLogin.setOnClickListener {
                    activity?.finish()
                }
                registerBtnNext.setOnClickListener {
                    presenter.create(
                        registerEditEmail.text.toString()
                    )
                }

                registerEditEmail.addTextChangedListener(watcher)
                registerEditEmail.addTextChangedListener(TxtWatcher {
                    displayEmailFailure(null)
                })
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
        binding = null
        fragmentAttachListener = null
        presenter.onDestroy()
        super.onDestroy()
    }

    private val watcher = TxtWatcher {
        binding?.registerBtnNext?.isEnabled =
            binding?.registerEditEmail?.text.toString().isNotEmpty()
    }

    override fun showProgress(enabled: Boolean) {

    }

    override fun displayEmailFailure(emailError: Int?) {
        binding?.registerEmailInput?.error = emailError?.let { getString(it) }
    }

    override fun onEmailFailure(message: String) {
        binding?.registerEmailInput?.error = message
    }

    override fun gotoNameAndPasswordSrceen(email: String) {
        fragmentAttachListener?.goToNameAmdPasswordScreen(email)
    }

}