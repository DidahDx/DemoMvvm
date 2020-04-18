package com.didahdx.mvvmsampleapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.didahdx.mvvmsampleapp.R
import com.didahdx.mvvmsampleapp.Utils.hide
import com.didahdx.mvvmsampleapp.Utils.show
import com.didahdx.mvvmsampleapp.Utils.snackbar
import com.didahdx.mvvmsampleapp.data.db.entities.User
import com.didahdx.mvvmsampleapp.databinding.ActivityMainBinding
import com.didahdx.mvvmsampleapp.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


/**
 *
 * used as Login
 *
 * */
class MainActivity : AppCompatActivity(), AuthListener,KodeinAware {

    override val kodein by kodein()
    private val factory :AuthViewModelFactory by instance()

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

        //used to bind with the layout
        val bindingMainActivity: ActivityMainBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)

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
