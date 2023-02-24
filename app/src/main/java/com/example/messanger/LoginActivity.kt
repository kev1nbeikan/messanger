package com.example.messanger

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.messanger.databinding.ActivityLoginBinding
import com.example.messanger.firebase.logInUser

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(this.layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.loginButtonLoginScreen.setOnClickListener {
            logIn()
        }
        binding.backRegisteButtoLoginScreen.setOnClickListener {
            finish()
        }
    }

    private fun logIn() {
        val password = binding.passwordEditTextLoginScreen.text.toString()
        val email = binding.mailEditTextLoginScreen.text.toString()
        logInUser(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Fail ${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}