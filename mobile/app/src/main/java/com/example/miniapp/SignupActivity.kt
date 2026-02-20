package com.example.miniapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miniapp.api.RetrofitClient
import com.example.miniapp.model.RegisterRequest
import com.example.miniapp.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val tvLogin = findViewById<TextView>(R.id.tvLogin)
        tvLogin.setOnClickListener { finish() }

        val etFirstName = findViewById<EditText>(R.id.etFirstName)
        val etLastName = findViewById<EditText>(R.id.etLastName)
        val etDob = findViewById<EditText>(R.id.etDob)
        val etEmail = findViewById<EditText>(R.id.etSignupEmail)
        val etPassword = findViewById<EditText>(R.id.etSignupPassword)
        val btnSignup = findViewById<Button>(R.id.btnSignup)

        btnSignup.setOnClickListener {
            val firstName = etFirstName.text.toString().trim()
            val lastName = etLastName.text.toString().trim()
            val dob = etDob.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val request = RegisterRequest(firstName, lastName, dob, email, password)

            RetrofitClient.instance.register(request).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful && response.body() != null) {
                        Toast.makeText(this@SignupActivity, "Registration successful!", Toast.LENGTH_LONG).show()
                        finish() // Automatically closes this screen and takes them back to Login!
                    } else {
                        Toast.makeText(this@SignupActivity, "Registration failed. Email might exist.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@SignupActivity, "Network error: Make sure Spring Boot is running!", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}