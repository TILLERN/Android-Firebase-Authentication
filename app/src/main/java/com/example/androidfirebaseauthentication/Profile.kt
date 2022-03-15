package com.example.androidfirebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

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

    val firstName = findViewById<TextView>(R.id.tvFistName)
    val lastName = findViewById<TextView>(R.id.tvLastName)
    val userName = findViewById<TextView>(R.id.tvUsername)
    val logOutbutton = findViewById<Button>(R.id.btnLogOut)


    private fun userProfile(){
        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)

        userName.text = "Email-->" + user?.email

        userreference?.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                firstName.text = "First Name-->" + snapshot.child("firstname").value.toString()
                lastName.text = "Last name-->" + snapshot.child("lastname").value.toString()
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        logOutbutton.setOnClickListener(){
            auth.signOut()
            startActivity(Intent(this@Profile, LogIn::class.java))
            finish()
        }


    }
}