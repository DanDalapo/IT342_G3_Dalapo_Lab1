package com.example.miniapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miniapp.api.RetrofitClient
import com.example.miniapp.model.LoginRequest
import com.example.miniapp.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Find all your UI elements
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvSignup = findViewById<TextView>(R.id.tvSignup)

        // 2. Keep your existing Sign Up navigation
        tvSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        // 3. Listen for the Login button click
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Basic validation so we don't send empty data to Spring Boot
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Stop running the code here
            }

            // Create the data envelope matching your Spring Boot model
            val request = LoginRequest(email, password)

            // Send the request in the background
            RetrofitClient.instance.login(request).enqueue(object : Callback<User> {

                // This runs if the server actually sends a response back (even an error response like 401)
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful && response.body() != null) {
                        // HTTP 200 OK: Login worked!
                        val user = response.body()!!
                        Toast.makeText(this@MainActivity, "Welcome back, ${user.firstName}!", Toast.LENGTH_LONG).show()

                        // NOTE: Later on, you would put an Intent here to navigate to a "Home" screen!
                    } else {
                        // HTTP 401 / 403: Wrong password or email
                        Toast.makeText(this@MainActivity, "Login failed. Check your credentials.", Toast.LENGTH_SHORT).show()
                    }
                }

                // This runs if the Android emulator completely fails to reach the server (e.g. server is off)
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Network error: Make sure Spring Boot is running!", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}