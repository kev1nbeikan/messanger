package com.example.messanger

import android.app.Activity
import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.messanger.databinding.ActivityRegisterBinding
import com.example.messanger.firebase.register
import com.example.messanger.misc.showShortToast
import com.example.messanger.misc.showSuccessToast
import com.example.messanger.misc.showWrongInputToast
import com.example.messanger.misc.translate

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    lateinit var loginButton: Button
    lateinit var alreadyHaveAccountButton: TextView
    lateinit var emailTextEdit: TextView
    lateinit var passwordEditText: TextView
    lateinit var usernameEditText: TextView
    lateinit var selectPhotoButton: Button
    lateinit var startForResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        setContentView(binding.root)
        setOnActivitySelectPhotoResult()
        setOnClickListeners()
    }

    private fun initBinding() {
        binding = ActivityRegisterBinding.inflate(this.layoutInflater)
        loginButton = binding.loginButtonRegisterScreen
        passwordEditText = binding.passwordEditTextRegisterScreen
        emailTextEdit = binding.emailTextEditRegisterScreen
        selectPhotoButton = binding.selectPhotoRegisterScreen
        alreadyHaveAccountButton = binding.alreadyHaveAccoutTextViewRegister
    }

    private fun setOnClickListeners() {
        loginButton.setOnClickListener { registerUser() }
        alreadyHaveAccountButton.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
        }
        selectPhotoButton.setOnClickListener {
            openActivityForResult()
        }
    }


    private fun setOnActivitySelectPhotoResult() {
        startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val pickedPhoto = result.data
                    if (pickedPhoto?.data != null) {
                        if (Build.VERSION.SDK_INT >= 28) {
                            val source = ImageDecoder.createSource(contentResolver, pickedPhoto.data!!)
                            val pickedBitmap = ImageDecoder.decodeBitmap(source)
                        } else {
                            val pickedBitmap = MediaStore.Images.Media.getBitmap(contentResolver, pickedPhoto.data)
                        }
//                        TODO(change background of selectPhotoButton)
                    }
                }
            }
    }

    private fun openActivityForResult() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startForResult.launch(intent)
    }


    private fun registerUser() {
        val email = emailTextEdit.text.toString()
        val password = passwordEditText.text.toString()
        val nickname = usernameEditText.text.toString()
        if (password.isEmpty() || email.isEmpty()) {
            showWrongInputToast(this)
            return
        }
        register(nickname, email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                showSuccessToast(this)
            } else {
                showShortToast(this, translate(it.exception?.message.toString()))
            }
        }
    }
}