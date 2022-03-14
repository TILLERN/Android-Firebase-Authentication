package com.example.androidfirebaseauthentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {
    lateinit var auth:FirebaseAuth

    //realtime database
    var databaseReference: DatabaseReference? = null
    //database
    var database: FirebaseDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth =  FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        register()
    }

    val firstNameInput = findViewById<EditText>(R.id.etFirstName)
    val lastNameInput = findViewById<EditText>(R.id.etLastName)
    val userNameInput = findViewById<EditText>(R.id.etUserName)
    val passwordInput = findViewById<EditText>(R.id.etRegisterPassword)
    val registerButton = findViewById<Button>(R.id.btnRegister)

    private fun register(){
        registerButton.setOnClickListener(){
            if (TextUtils.isEmpty(firstNameInput.text.toString())){
                firstNameInput.setError("Please enter First Name")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(lastNameInput.text.toString())){
                lastNameInput.setError("Please enter Last Name")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(userNameInput.text.toString())){
                userNameInput.setError("Please enter a Username")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(passwordInput.text.toString())){
                passwordInput.setError("Please enter a Password")
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(userNameInput.text.toString(),passwordInput.text.toString())
                .addOnCompleteListener{
                    if (it.isSuccessful){
                        val currentUser = auth.currentUser
                        val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                        currentUserDb?.child("firstname")?.setValue(firstNameInput.text.toString())
                        currentUserDb?.child("lastname")?.setValue(lastNameInput.text.toString())

                        Toast.makeText(this@Register, "Registration Successful!", Toast.LENGTH_LONG).show()

finish()

                    }else{
                        Toast.makeText(this@Register, "Registration failed! Please try again later.", Toast.LENGTH_LONG).show()
                    }
                }

        }


    }
}