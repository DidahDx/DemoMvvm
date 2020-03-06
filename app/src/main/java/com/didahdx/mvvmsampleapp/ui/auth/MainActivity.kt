package com.didahdx.mvvmsampleapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.didahdx.mvvmsampleapp.R
import com.didahdx.mvvmsampleapp.Utils.hide
import com.didahdx.mvvmsampleapp.Utils.show
import com.didahdx.mvvmsampleapp.Utils.toast
import com.didahdx.mvvmsampleapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
 * used as Login
 *
 * */
class MainActivity : AppCompatActivity(), AuthListener {

    override fun onStarted() {
       progress_bar.show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer{
            toast(it)
            progress_bar.hide()
        })
    }

    override fun onFailure(errorMessage: String) {
        progress_bar.hide()
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
