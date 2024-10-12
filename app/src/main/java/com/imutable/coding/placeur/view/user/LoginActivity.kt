package com.imutable.coding.placeur.view.user

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.imutable.coding.placeur.R
import com.imutable.coding.placeur.databinding.ActivityLoginBinding
import com.imutable.coding.placeur.model.UserAuth
import com.imutable.coding.placeur.util.NetworkResult
import com.imutable.coding.placeur.view.place.HomeActivity
import com.imutable.coding.placeur.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        binding.loginSignUp.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
            setupObserver()
        }
    }

    private fun setupObserver() {
        var user = getInputValues()
        binding.loginProgressBar.visibility = View.VISIBLE
        userViewModel.authenticateUserResponse(user)
        userViewModel.authResponse.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.loginProgressBar.visibility = View.GONE
                    response.data?.let {
                        val intent = Intent(this,HomeActivity::class.java)
                        startActivity(intent)
                    }
                }
                is NetworkResult.Error -> {
                    binding.loginProgressBar.visibility = View.GONE
                    response.message.let {
                        if (it != null) {
                            Snackbar.make(binding.root, it, 3000).show()
                        }
                    }
                }

                is NetworkResult.Loading -> {
                    binding.loginProgressBar.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun getInputValues(): UserAuth {

        return UserAuth(
            binding.loginTextInputEditTextUserId.text.toString(),
            binding.loginTextInputEditTextPassword.text.toString(),
            ""
        )

    }
}