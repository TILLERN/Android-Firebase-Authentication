package com.example.androidfirebaseauthentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Profile : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    //realtime database
    var databaseReference: DatabaseReference? = null
    //database
    var database: FirebaseDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth =  FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        userProfile()
    }

    private fun userProfile(){
        val user = auth.currentUser
        databaseReference?.child(user?.uid!!)
    }
}