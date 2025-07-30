package com.moran.retotecnico.core.firebase

import com.google.firebase.auth.FirebaseAuth

class CustomFirebaseAuth {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signUp(
        email: String,
        password: String,
        result: (Result<FirebaseAuthUi>) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    println("createUserWithEmail:success")
                    val user = auth.currentUser
                    val response =
                        Result.success(FirebaseAuthUi(user?.uid.orEmpty()))
                    result(response)
                } else {
                    // If sign in fails, display a message to the user.
                    println("createUserWithEmail:failure t${task.exception}")
                    val response =
                        Result.failure<FirebaseAuthUi>(Throwable(message = task.exception?.message.orEmpty()))
                    result(response)
                }
            }
    }

    fun auth(
        email: String,
        password: String,
        result: (Result<FirebaseAuthUi>) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    println("signInWithEmail:success")
                    val user = auth.currentUser
                    val response =
                        Result.success(FirebaseAuthUi(user?.uid.orEmpty()))
                    result(response)

                } else {
                    // If sign in fails, display a message to the user.
                    println("signInWithEmail:failure")
                    val response =
                        Result.failure<FirebaseAuthUi>(Throwable(message = task.exception?.message.orEmpty()))
                    result(response)
                }
            }
    }
}