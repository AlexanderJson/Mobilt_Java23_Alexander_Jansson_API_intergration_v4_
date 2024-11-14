package com.example.bankapp.Users.Authorization

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.bankapp.Users.service.UserService
import com.example.nyilnmning.R
import com.example.nyilnmning.view.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var authService: UserService
    private val userViewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //menu for login -> register activities
        val loginLayout = view.findViewById<android.widget.LinearLayout>(R.id.loginMenuLayout)
        val loginMenuItem = view.findViewById<android.widget.TextView>(R.id.loginMenuItem)
        val registerMenuItem = view.findViewById<android.widget.TextView>(R.id.registerMenuItem)

        //login form

        val emailEditText = view.findViewById<android.widget.EditText>(R.id.emailEditText)
        val passwordEditText = view.findViewById<android.widget.EditText>(R.id.passwordEditText)
        val loginButton = view.findViewById<android.widget.Button>(R.id.loginButton)
        // authentication for login

        userViewModel.fetchAllUsers()

        userViewModel.userLiveData.observe(viewLifecycleOwner){ result ->
            result.onSuccess { user ->
                Toast.makeText(requireContext(), "Welcome, ${user?.username}!", Toast.LENGTH_SHORT).show()
            }.onFailure { exception ->
                Toast.makeText(requireContext(), exception.message, Toast.LENGTH_SHORT).show()
            }
        }

        // go to register activity
        registerMenuItem.setOnClickListener {

            val rotationAnimation = ObjectAnimator.ofFloat(loginLayout,  "rotationY", 0f, 180f)
            rotationAnimation.duration = 500
            rotationAnimation.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    (activity as? AuthActivity)?.switchToRegistration()
                }
            })
            rotationAnimation.start()
        }

        // log inp
        loginButton.setOnClickListener {
            val username = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            userViewModel.authorizeUser(username,password, requireContext())
            Toast.makeText(requireContext(), "${username} + ${password}", Toast.LENGTH_SHORT).show()


        }
    }


}



