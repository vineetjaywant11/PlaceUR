package com.imutable.coding.placeur.view.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import coil.transform.RoundedCornersTransformation
import com.imutable.coding.placeur.R
import com.imutable.coding.placeur.data.local.dao.UserDao
import com.imutable.coding.placeur.databinding.ActivitySignUpBinding
import com.imutable.coding.placeur.model.User
import com.imutable.coding.placeur.util.NetworkResult
import com.imutable.coding.placeur.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.registration = userViewModel
        binding.lifecycleOwner = this
        binding.signupButton.setOnClickListener {
            setupObserver()
        }
        binding.signupSignIn.setOnClickListener {
            finish()
        }
    }

    private fun setupObserver() {
        var user = getInputValues()
        binding.signupProgressBar.visibility = View.VISIBLE
        userViewModel.registerUserResponse(user)
        userViewModel.registerResponse.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.signupProgressBar.visibility = View.GONE
                    response.data?.let {
                        userViewModel.saveUserData(it)
                        Toast.makeText(
                            this,
                            "Registered Successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }
                is NetworkResult.Error -> {
                    binding.signupProgressBar.visibility = View.GONE
                    Toast.makeText(
                        this,
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Loading -> {
                    binding.signupProgressBar.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun getInputValues(): User {

        return User(
            binding.signupTextInputEditTextName.text.toString(),
            binding.signupTextInputEditTextPhoneNumber.text.toString(),
            binding.signupTextInputEditTextPassword.text.toString(),
            binding.signupTextInputEditTextEmail.text.toString()
        )

    }
}