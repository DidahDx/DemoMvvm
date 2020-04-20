package com.didahdx.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import com.didahdx.mvvmsampleapp.data.repositories.UserRepository

class ProfileViewModel(repository: UserRepository) : ViewModel() {

    val user=repository.getUser()
}
