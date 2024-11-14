package com.example.nyilnmning.api

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore


class firebaseInit {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("movie-app")

    fun logUsersContent() {
        usersCollection.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result) {
                    Log.d("FirestoreLog", "${document.id} => ${document.data}")
                }
            } else {
                Log.e("FirestoreLog", "Error getting documents: ", task.exception)
            }
        }
    }



}