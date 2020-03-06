package com.didahdx.mvvmsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    var email: String? = null
    var passowrd: String? = null
    var authListener:AuthListener?=null

    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || passowrd.isNullOrEmpty()){
            // failed
            authListener?.onFailure("Invalid email or password ")
            return
        }
        //success
        authListener?.onSuccess()
    }

}