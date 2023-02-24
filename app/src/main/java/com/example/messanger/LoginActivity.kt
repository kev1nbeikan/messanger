package com.example.messanger

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.messanger.databinding.ActivityLoginBinding
import com.example.messanger.firebase.logInUser
import com.example.messanger.misc.showShortToast
import com.example.messanger.misc.showSuccessToast
import com.example.messanger.misc.showWrongInputToast
import com.example.messanger.misc.translate

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var loginButton: Button
    lateinit var backRegisterButton: TextView
    lateinit var passwordEditText: TextView
    lateinit var mailEditText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        setContentView(binding.root)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        loginButton.setOnClickListener { logIn() }
        backRegisterButton.setOnClickListener { finish() }
    }

    private fun initBinding() {
        binding = ActivityLoginBinding.inflate(this.layoutInflater)
        loginButton = binding.loginButtonLoginScreen
        backRegisterButton = binding.backRegisterButtonLoginScreen
        passwordEditText = binding.passwordEditTextLoginScreen
        mailEditText = binding.mailEditTextLoginScreen
    }

    private fun logIn() {
        val password = passwordEditText.text.toString()
        val email = mailEditText.text.toString()
        if (password.isEmpty() || email.isEmpty()) {
            showWrongInputToast(this)
            return
        }
        logInUser(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                showSuccessToast(this)
            } else {
                showShortToast(this, translate(it.exception?.message.toString()))
            }
        }
    }
}