package com.example.bankapp.Users.service

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.bankapp.Users.repository.UserRepository
import com.example.nyilnmning.model.Movie
import com.example.nyilnmning.model.User
import com.example.nyilnmning.view.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(private val userRepository: UserRepository) {


    suspend fun registerUser(username: String, password: String, context: Context){
            try {
            val user = User(username = username, password = password)
            userRepository.registerUser(user)

        } catch (e: Exception){
            Log.e("UserService", "Error, couldnt register")
        }
    }



    suspend fun fetchAllUsers() {
        val result = userRepository.getAllUsers()
        result.onSuccess { users ->
            // Handle the list of users
            Log.d("fetchAllGood", "Users: $users")
        }.onFailure { exception ->
            // Handle the error
            Log.e("fetchAllFail", "Error: ${exception.message}")
        }
    }




    suspend fun authorize(username: String, password: String, context: Context): Result<User?> {
        return try {
            Log.d("UserViewModel Service", username + "  " + password)
            val result = userRepository.getUser(username)
            if (result.isSuccess) {
                val user = result.getOrNull()
                if (user != null && user.password == password) {

                    val token = user.username
                    val masterKey = MasterKey.Builder(context)
                        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                        .build()

                    val sharedPreferences = EncryptedSharedPreferences.create(
                        context,
                        "MyPrefs",
                        masterKey,
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                    )
                    sharedPreferences.edit().putString("token", token).apply()
                    Log.d("LoginFragment", " Token: $token")


                    val intent = Intent(context, MainActivity::class.java)
                    // tar bort auth ur stack så man inte kan gå tbx utan att logga ut
                    // ( viktigt för att kunna rensa shared prefs osv ordentligt..)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                    Result.success(user)

                } else {
                    Result.failure(Exception("Authentication failed"))
                }
            } else {
                Result.failure(Exception("Failed to retrieve user"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error occurred", e))
        }
    }
}


