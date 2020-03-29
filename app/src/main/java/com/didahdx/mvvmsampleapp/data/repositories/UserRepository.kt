package com.didahdx.mvvmsampleapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.didahdx.mvvmsampleapp.data.db.AppDatabase
import com.didahdx.mvvmsampleapp.data.db.entities.User
import com.didahdx.mvvmsampleapp.data.network.MyApi
import com.didahdx.mvvmsampleapp.data.network.SafeApiRequest
import com.didahdx.mvvmsampleapp.data.network.response.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    //used to call remote repository
    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest {
            api.userLogin(email, password)
        }
    }

    fun getUser() = db.getUserDao().getUser()


    suspend fun saveUser(user: User) = db.getUserDao().updateInsert(user)
}