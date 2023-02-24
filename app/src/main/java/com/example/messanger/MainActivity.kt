package com.example.messanger

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.messanger.databinding.ActivityMainBinding
import com.example.messanger.firebase.register

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(this.layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.loginButtonRegisterScreen.setOnClickListener { registerUser() }
        binding.alreadyHaveAccoutTextViewRegister.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser() {
        val email = binding.emailTextEditRegisterScreen.text.toString()
        val password = binding.passwordEditTextRegisterScreen.text.toString()
        register("someone", email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}