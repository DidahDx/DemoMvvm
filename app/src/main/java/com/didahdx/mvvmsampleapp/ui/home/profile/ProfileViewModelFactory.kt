package com.didahdx.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.didahdx.mvvmsampleapp.data.repositories.UserRepository
import com.didahdx.mvvmsampleapp.ui.auth.AuthViewModel

class ProfileViewModelFactory(private val repository: UserRepository): ViewModelProvider.NewInstanceFactory() {

    /**
     * used to pass arguments from activity to viewmodel
     * */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }
}