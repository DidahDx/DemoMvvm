package com.didahdx.mvvmsampleapp.data.network.response

import com.didahdx.mvvmsampleapp.data.db.entities.User

/**
 * used to parse json response
 * */
data class AuthResponse (
    val isSuccessful: String?,
    val message:String,
    val user:User
)