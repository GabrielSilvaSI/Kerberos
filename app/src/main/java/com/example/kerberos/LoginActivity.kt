package com.example.kerberos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        checkAuth()
    }

    private fun checkAuth(){
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}