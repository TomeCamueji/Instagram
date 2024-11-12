package com.example.instagram.login.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.commom.base.DependencyInjector
import com.example.instagram.commom.view.TxtWatcher
import com.example.instagram.databinding.ActivityLoginBinding
import com.example.instagram.login.Login
import com.example.instagram.login.presentation.LoginPresenter
import com.example.instagram.main.view.MainActivity
import com.example.instagram.register.v.view.RegisterActivity

class LoginActivity : AppCompatActivity(), Login.view {
    private lateinit var binding: ActivityLoginBinding

    override lateinit var presenter: Login.presenter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        presenter = LoginPresenter(this,DependencyInjector.loginRepository())
        with(binding) {
            loginEditEmail.addTextChangedListener(watcher)
            loginEditEmail.addTextChangedListener(TxtWatcher {
                displayEmailFailure(null)
            })
            loginEditPassword.addTextChangedListener(watcher)
            loginEditPassword.addTextChangedListener(TxtWatcher {
                displasyPasswordFailure(null)
            })
            loginBtnEnter.setOnClickListener {
                presenter.login(loginEditEmail.text.toString(), loginEditPassword.text.toString())
            }

            loginTxtRegister.setOnClickListener {
                gotoRegisterScreen()
            }
        }

    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private val watcher = TxtWatcher {

        binding.loginBtnEnter.isEnabled = binding.loginEditEmail.text.toString().isNotEmpty()
                && binding.loginEditPassword.text.toString().isNotEmpty()
    }
    private fun gotoRegisterScreen(){
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    override fun showProgress() {

    }

    override fun displayEmailFailure(emailError: Int?) {
        binding.loginEmailInput.error = emailError?.let { getString(it) }
    }

    override fun displasyPasswordFailure(passwordError: Int?) {
        binding.loginPasswordInput.error = passwordError?.let { getString(it) }
    }

    override fun onUserAuthenticated() {
        // Ir para tela principal
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    override fun onUserUnauthorized(messege:String) {
        // mostrar alerta
        Toast.makeText(this,messege,Toast.LENGTH_LONG).show()
    }
}