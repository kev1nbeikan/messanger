package com.example.messanger.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

fun register(name: String, email: String, password: String): Task<AuthResult> {
    return FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
}

fun getUser(): FirebaseUser? {
    return FirebaseAuth.getInstance().currentUser
}

fun logInUser(email: String, password: String): Task<AuthResult> {
    return Firebase.auth.signInWithEmailAndPassword(email, password)
}