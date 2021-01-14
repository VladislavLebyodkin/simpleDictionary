package com.example.simpledictionary.register.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simpledictionary.R
import com.example.simpledictionary.databinding.RegisterFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModel()
    private lateinit var binding: RegisterFragmentBinding

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
    }
}