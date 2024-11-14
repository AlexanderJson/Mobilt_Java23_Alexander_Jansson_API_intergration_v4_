package com.example.bankapp.Users.Authorization

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.bankapp.Users.service.UserService
import com.example.nyilnmning.R
import com.example.nyilnmning.view.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private lateinit var authService: UserService
    private val userViewModel: UserViewModel by viewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            val registerButton = view.findViewById<android.widget.Button>(R.id.registerButton   )
            val emailEditText = view.findViewById<android.widget.EditText>(R.id.emailEditText)
            val passwordEditText = view.findViewById<android.widget.EditText>(R.id.passwordEditText)
            val userFormLayout = view.findViewById<LinearLayout>(R.id.user_form)

            val loginMenuItem = view.findViewById<android.widget.TextView>(R.id.loginMenuItem)

        // go to register activity
        loginMenuItem.setOnClickListener {

            val rotationAnimation = ObjectAnimator.ofFloat(userFormLayout,  "rotationY", 0f, 180f)
            rotationAnimation.duration = 500
            rotationAnimation.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    (activity as? AuthActivity)?.switchToLogin()
                }
            })
            rotationAnimation.start()
        }



        registerButton.setOnClickListener {
                val username = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                userViewModel.registerUser(username,password, requireContext())
            }

        }



    }






