package com.example.androidfirebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        auth = FirebaseAuth.getInstance()

        login()
    }

    val loginUsernameInput = findViewById<EditText>(R.id.etEmail)
    val loginPasswordInput = findViewById<EditText>(R.id.etPassword)
    val loginButton = findViewById<Button>(R.id.btnLogin)


    private fun login(){
        loginButton.setOnClickListener(){
            if (TextUtils.isEmpty(loginUsernameInput.text.toString())){
                loginPasswordInput.setError("Please enter username")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(loginPasswordInput.text.toString())){
                loginPasswordInput.setError("Enter password")
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(loginUsernameInput.text.toString(), loginPasswordInput.text.toString())
                .addOnCompleteListener(){
                    if (it.isSuccessful){
                        startActivity(Intent(this@LogIn,Profile::class.java))
                        finish()
                    }else{
                        Toast.makeText(this@LogIn, "Login failed, please enter the username or password.", Toast.LENGTH_LONG).show()
                    }
                }
        }


    }
}