package com.didahdx.mvvmsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.didahdx.mvvmsampleapp.Utils.ApiException
import com.didahdx.mvvmsampleapp.Utils.Coroutines
import com.didahdx.mvvmsampleapp.Utils.NoInternetException
import com.didahdx.mvvmsampleapp.data.repositories.UserRepository

class AuthViewModel (private val userRepository: UserRepository): ViewModel() {

    var email: String? = null
    var password: String? = null
    var authListener:AuthListener?=null

    fun getLoggedInUser()=userRepository.getUser()

    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            // failed
            authListener?.onFailure("Invalid email or password ")
            return
        }

        Coroutines.main {
            //success
           try {
               val loginResponse = userRepository.userLogin(email!!, password!!)
               loginResponse.user.let{
                   userRepository.saveUser(it)
                   authListener?.onSuccess(it)
                   return@main
               }
               authListener?.onFailure(loginResponse.message!!)
           }catch (e:ApiException){
               authListener?.onFailure(e.message!!)
           }catch (e:NoInternetException){
               authListener?.onFailure(e.message!!)
           }
        }

    }

}