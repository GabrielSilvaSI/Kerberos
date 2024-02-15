package com.example.kerberos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var editTextEmailSignup: EditText
    private lateinit var editTextPasswordSignup: EditText
    private lateinit var editTextRepeatPasswordSignup: EditText
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        editTextEmailSignup = findViewById(R.id.editTextEmailSignup)
        editTextPasswordSignup = findViewById(R.id.editTextPasswordSignup)
        editTextRepeatPasswordSignup = findViewById(R.id.editTextRepeatPasswordSignup)


        auth = Firebase.auth
        checkAuth()

        val buttonSignup = findViewById<Button>(R.id.buttonSignup)
        buttonSignup.setOnClickListener {
            if (camposSaoValidos()) {
                cadastrarUsuario()
            }
        }
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        buttonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun camposSaoValidos(): Boolean {
        val email = editTextEmailSignup.text.toString()
        val password = editTextPasswordSignup.text.toString()
        val repeatPassword = editTextRepeatPasswordSignup.text.toString()
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmailSignup.error = "Por favor, insira um email válido"
            return false
        }
        if (password.length < 6) {
            editTextPasswordSignup.error = "A senha deve ter pelo menos 6 caracteres"
            return false
        }
        if (password != repeatPassword) {
            editTextRepeatPasswordSignup.error = "As senhas não coincidem"
            return false
        }
        return true
    }

    private fun cadastrarUsuario() {
        val email = editTextEmailSignup.text.toString()
        val password = editTextPasswordSignup.text.toString()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                    addEmailToDatabase(email)
                    checkAuth()
                } else {
                    Toast.makeText(this, "Erro ao tentar cadastrar", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addEmailToDatabase(email: String) {
        databaseReference = FirebaseDatabase.getInstance().reference.child("emails")

        databaseReference.push().setValue(email)
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