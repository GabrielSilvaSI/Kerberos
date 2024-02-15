package com.example.kerberos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.google.firebase.auth.FirebaseAuth

class CreateChatActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_chat)

        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmailCC)

        auth = FirebaseAuth.getInstance()

        val buttonListBack = findViewById<ImageButton>(R.id.buttonListBack)
        buttonListBack.setOnClickListener {
            finish()
        }
        val buttonStartChat = findViewById<Button>(R.id.buttonStartChat)
        buttonStartChat.setOnClickListener {
            //searchEmail(editTextEmail.text.toString())
        }

    }
}