package com.didahdx.mvvmsampleapp.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.didahdx.mvvmsampleapp.Utils.ApiException
import com.didahdx.mvvmsampleapp.Utils.Coroutines
import com.didahdx.mvvmsampleapp.Utils.NoInternetException
import com.didahdx.mvvmsampleapp.data.repositories.UserRepository

class AuthViewModel (private val userRepository: UserRepository): ViewModel() {

    var name:String?=null
    var email: String? = null
    var password: String? = null
    var passwordConfirm:String?=null
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

    fun onLogin(view: View){
        Intent(view.context,MainActivity::class.java).also {
            view.context.startActivity(it)

        }

    }

    fun onSignUp(view: View){
        Intent(view.context,SignUpActivity::class.java).also {
            view.context.startActivity(it)
        }
    }


    fun onSignUpButtonClick(view: View){
        authListener?.onStarted()
        if(name.isNullOrEmpty() ){
            // failed
            authListener?.onFailure("name is required")
            return
        }

        if(email.isNullOrEmpty() ){
            // failed
            authListener?.onFailure("email is required")
            return
        }

        if(password.isNullOrEmpty() || passwordConfirm.isNullOrEmpty()){
            // failed
            authListener?.onFailure("password is required")
            return
        }

        if(password!=passwordConfirm){
            // failed
            authListener?.onFailure("passwords did not match")
            return
        }

        Coroutines.main {
            //success
            try {
                val loginResponse = userRepository.userSignUp(name!!,email!!, password!!)
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