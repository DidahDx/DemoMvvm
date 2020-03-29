package com.didahdx.mvvmsampleapp.ui.auth

import androidx.lifecycle.LiveData
import com.didahdx.mvvmsampleapp.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user:User)
    fun onFailure(errorMessage:String)
}