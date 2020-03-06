package com.didahdx.mvvmsampleapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.didahdx.mvvmsampleapp.R
import com.didahdx.mvvmsampleapp.Utils.toast
import com.didahdx.mvvmsampleapp.databinding.ActivityMainBinding

/**
 *
 * used as Login
 *
 * */
class MainActivity : AppCompatActivity(), AuthListener {

    override fun onStarted() {
        toast("Login started")
    }

    override fun onSuccess() {
        toast("Login success")
    }

    override fun onFailure(errorMessage: String) {
        toast("Login failed $errorMessage")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //used to bind with the layout
        val bindingMainActivity: ActivityMainBinding = DataBindingUtil
            .setContentView(this,R.layout.activity_main)

        val viewModel=ViewModelProviders.of(this).get(AuthViewModel::class.java)

        //binds the data with the ui
        bindingMainActivity.viewmodel=viewModel
        viewModel.authListener=this
    }


}
