package com.example.messanger

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.util.UUID

fun register(name: String, email: String, password: String): Task<AuthResult> {
    return FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
}

fun getUser(): FirebaseUser? {
    return FirebaseAuth.getInstance().currentUser
}

fun logInUser(email: String, password: String): Task<AuthResult> {
    return Firebase.auth.signInWithEmailAndPassword(email, password)
}

fun uploadFile(file: Uri, diroctary: String): UploadTask {
    val filename = UUID.randomUUID().toString()
    val ref = FirebaseStorage.getInstance().getReference("/$diroctary/$filename")
    return ref.putFile(file)
}

fun uploadImage(image: Uri) = uploadFile(image, "images")


