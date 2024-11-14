package com.example.nyilnmning.view

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankapp.Users.service.UserService
import com.example.nyilnmning.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userService: UserService) : ViewModel()
{
    private val userData = MutableLiveData<Result<User?>>()
    val userLiveData: LiveData<Result<User?>> get() = userData

    fun authStatus(): Boolean{
        val result = userData.value
        return result != null && result.isSuccess && result.getOrNull() != null
    }

    fun fetchAllUsers() {
        viewModelScope.launch {
            val result = userService.fetchAllUsers()

            Log.d("users: ", result.toString()

            )
        }
    }



    fun authorizeUser(username: String, password: String, context: Context){
        viewModelScope.launch {
            val result = userService.authorize(username, password, context)
            Log.d("UserViewModel", "authorizeUser result:" + result + username + password)
            userData.postValue(result)
        }
    }

    fun registerUser(username:String, password: String, context: Context) {

        viewModelScope.launch {
            userService.registerUser(username,password, context)
        }
    }

}