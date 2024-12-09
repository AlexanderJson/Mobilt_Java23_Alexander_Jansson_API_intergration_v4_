package com.example.bankapp.Users.repository


import android.util.Log
import com.example.nyilnmning.model.Movie
import com.example.nyilnmning.model.User
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository  @Inject constructor() {


    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")
    private val userIDDoc = db.collection("userID").document("userID")






    suspend fun registerUser(user: User): Result<String>{
        return withContext(Dispatchers.IO){
            try {
                db.runTransaction{ transaction ->
                    val snapshot = transaction.get(userIDDoc)
                    val currentID = snapshot.getLong("value") ?: 0

                    val newID = currentID + 1
                    transaction.update(userIDDoc, "value", newID)
                    user.userid = newID.toInt()
                    usersCollection.add(user)
                }
                Result.success("Registration succeded!")
            } catch (e: Exception){
                Log.e("UserRepository", "Error registering user", e)
                Result.failure(Exception("Failed to register user", e))
            }
        }
    }

    suspend fun getAllUsers(): Result<List<User>> {
        return withContext(Dispatchers.IO) {
            try {
                val querySnapshot = usersCollection.get().await()
                Log.d("UserRepository", "Query snapshot size: ${querySnapshot.size()}")
                val users = querySnapshot.documents.mapNotNull { document ->
                    Log.d("UserRepository", "Document data: ${document.data}")
                    document.toObject(User::class.java)
                }
                Log.d("UserRepository", "Parsed users: $users")
                Result.success(users)
            } catch (e: Exception) {
                Log.e("UserRepository", "Error retrieving users", e)
                Result.failure(Exception("Failed to retrieve users", e))
            }
        }
    }




    suspend fun getUser(username: String): Result<User?> {
        return withContext(Dispatchers.IO) {
            try {
                val querySnapshot = usersCollection.whereEqualTo("username", username).get().await()
                Log.d("1UserViewModel",username)
                val user = querySnapshot.documents[0].toObject(User::class.java)
                Log.d("1UserViewModels", user.toString())
                Result.success(user)
            } catch (e: Exception) {
                Result.failure(Exception("Failed to find user!"))
            }
        }

    }


}
