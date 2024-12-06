package com.example.nyilnmning.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bankapp.Users.Authorization.AuthActivity
import com.example.nyilnmning.Authorization.SharedPreferencesUtil
import com.example.nyilnmning.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val sharedPreferences = SharedPreferencesUtil
        val token = sharedPreferences.getToken(context = this)
        if (token != null){
            Toast.makeText(this, "User: ${token} is logged in!", Toast.LENGTH_SHORT).show()
            if (savedInstanceState == null) {
                // Add the default fragment
                val defaultFragment = NewMovieFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, defaultFragment)
                    .commit()
            }
        } else {
            Toast.makeText(this, "To login!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }



        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.nav_popularMovies -> {
                    val fragment = NewMovieFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit()
                    true
                }
                R.id.nav_randomMovie -> {
                    val fragment = RandomMovieFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit()
                    true
                }
                R.id.nav_searchMovie -> {
                    val fragment = RecommendedFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit()
                    true
                }
                R.id.nav_logout -> {
                    val intent = Intent(this, AuthActivity::class.java)
                    // rensar alla stacks och skapar ny för authActivity
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    // rensar shared prefs så ingen tidigsre info är med
                    sharedPreferences.cleanPreferences(context = this)
                    startActivity(intent)
                    finish()
                    true
                }

                else -> {
                    false
                }
            }
        }
}}

