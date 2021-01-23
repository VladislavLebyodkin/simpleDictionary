package com.example.simpledictionary.login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simpledictionary.R
import com.example.simpledictionary.databinding.LoginFragmentBinding
import com.example.simpledictionary.util.isValidEmail
import com.example.simpledictionary.util.isValidPassword
import com.example.simpledictionary.util.validate
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()
    lateinit var binding: LoginFragmentBinding

    private var isValidEmail = false
    private var isValidPassword = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = LoginFragmentBinding.inflate(layoutInflater, container, false)

        binding.btnSubmitLogin.setOnClickListener {
            viewModel.login(
                binding.inputEmail.text.toString(),
                binding.inputPassword.text.toString()
            )
        }

        binding.tvToRegister.setOnClickListener {
            viewModel.navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.inputEmail.validate(getString(R.string.input_email)) { field ->
            isValidEmail = field.isValidEmail()
            field.isValidEmail()
        }

        binding.inputPassword.validate(getString(R.string.short_password)) { field ->
            isValidPassword = field.isValidPassword()
            field.isValidPassword()
        }

        viewModel.navController = findNavController()
    }


}