package com.example.miniapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // 1. Find the new UI Elements from the card layout
        val tvWelcomeHeader = findViewById<TextView>(R.id.tvWelcomeHeader)
        val tvFullNameValue = findViewById<TextView>(R.id.tvFullNameValue)
        val tvEmailValue = findViewById<TextView>(R.id.tvEmailValue)
        // val tvDobValue = findViewById<TextView>(R.id.tvDobValue) // Keep for later
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // 2. Grab all the data passed from Login
        val firstName = intent.getStringExtra("USER_FIRST_NAME") ?: "User"
        val lastName = intent.getStringExtra("USER_LAST_NAME") ?: ""
        val email = intent.getStringExtra("USER_EMAIL") ?: "Not available"

        // 3. Set the text on the screen
        tvWelcomeHeader.text = "Welcome back, $firstName!"
        tvFullNameValue.text = "$firstName $lastName"
        tvEmailValue.text = email

        // 4. Handle Logout
        btnLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}