package com.didahdx.mvvmsampleapp.ui.auth

import android.content.Intent
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
import com.didahdx.mvvmsampleapp.Utils.snackbar
import com.didahdx.mvvmsampleapp.Utils.toast
import com.didahdx.mvvmsampleapp.data.db.AppDatabase
import com.didahdx.mvvmsampleapp.data.db.entities.User
import com.didahdx.mvvmsampleapp.data.network.MyApi
import com.didahdx.mvvmsampleapp.data.network.NetworkConnectionInterceptor
import com.didahdx.mvvmsampleapp.data.repositories.UserRepository
import com.didahdx.mvvmsampleapp.databinding.ActivityMainBinding
import com.didahdx.mvvmsampleapp.ui.home.HomeActivity
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

    override fun onSuccess(user: User) {
        progress_bar.hide()
        root_layout.snackbar("${user.name}  is logged in")
    }

    override fun onFailure(errorMessage: String) {
        progress_bar.hide()
        root_layout.snackbar("Login failed $errorMessage")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkConnectionInterceptor=NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val db = AppDatabase(this)
        val repository = UserRepository(api, db)
        val factory = AuthViewModelFactory(repository)

        //used to bind with the layout
        val bindingMainActivity: ActivityMainBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)

        //binds the data with the ui
        bindingMainActivity.viewmodel = viewModel
        viewModel.authListener = this
        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }


}
