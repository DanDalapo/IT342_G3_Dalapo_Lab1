package com.example.miniapp.model

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val dob: String,
    val email: String,
    val password: String
)