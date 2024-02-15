package com.example.kerberos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class ListActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        auth = Firebase.auth

        val buttonExit = findViewById<ImageButton>(R.id.buttonExit)
        buttonExit.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            Firebase.auth.signOut()
            finish()
        }
        val buttonAddChat = findViewById<ImageButton>(R.id.buttonAddChat)
        buttonAddChat.setOnClickListener {
            val intent = Intent(this, CreateChatActivity::class.java)
            startActivity(intent)
        }
    }
}