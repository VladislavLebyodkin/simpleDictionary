package com.example.simpledictionary.register.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simpledictionary.R
import com.example.simpledictionary.databinding.RegisterFragmentBinding
import com.example.simpledictionary.util.isValidEmail
import com.example.simpledictionary.util.isValidPassword
import com.example.simpledictionary.util.validate
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModel()
    private lateinit var binding: RegisterFragmentBinding

    private var isValidEmail = false
    private var isValidPassword = false
    private var isValidPasswordConfirm = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegisterFragmentBinding.inflate(layoutInflater, container, false)

        binding.btnSubmitRegister.setOnClickListener {
            viewModel.register(
                binding.inputEmail.text.toString(),
                binding.inputPassword.text.toString(),
                binding.inputConfirmPassword.text.toString()
            )
        }

        binding.tvToLogin.setOnClickListener {
            viewModel.navController.navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navController = findNavController()

        binding.inputEmail.validate(getString(R.string.input_email)) { field ->
            isValidEmail = field.isValidEmail()
            field.isValidEmail()
        }

        binding.inputPassword.validate(getString(R.string.short_password)) { field ->
            isValidPassword = field.isValidPassword()
            field.isValidPassword()
        }

        binding.inputPassword.validate(getString(R.string.different_password)) { field ->
            isValidPassword = field == binding.inputPassword.text.toString()
            field == binding.inputPassword.text.toString()
        }

        viewModel.showError.observe(viewLifecycleOwner) {
            Toast.makeText(context, getString(R.string.smth_wrong), Toast.LENGTH_SHORT).show()
        }
    }
}