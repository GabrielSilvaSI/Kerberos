package com.example.kerberos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var editTextEmailLogin: EditText
    private lateinit var editTextPasswordLogin: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        checkAuth()
        
        editTextEmailLogin = findViewById(R.id.editTextEmailLogin)
        editTextPasswordLogin = findViewById(R.id.editTextPasswordLogin)

        val buttonEnter = findViewById<Button>(R.id.buttonEnter)
        buttonEnter.setOnClickListener {
            logarUsuario()
        }
        val buttonCreateAccount = findViewById<Button>(R.id.buttonCreateAccount)
        buttonCreateAccount.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun logarUsuario() {
        val email = editTextEmailLogin.text.toString()
        val password = editTextPasswordLogin.text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Usuário logado com sucesso", Toast.LENGTH_SHORT).show()
                    checkAuth()
                } else {
                    Toast.makeText(this, "Erro ao tentar logar", Toast.LENGTH_SHORT).show()
                }
            }
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