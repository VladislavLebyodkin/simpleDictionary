package com.vlados_project.simpledictionary.register.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vlados_project.simpledictionary.R
import com.vlados_project.simpledictionary.databinding.RegisterFragmentBinding
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
            if(viewModel.formIsValid()) {
                viewModel.onRegisterButtonClick(
                        binding.inputEmail.text.toString(),
                        binding.inputPassword.text.toString(),
                        binding.inputPasswordConfirm.text.toString()
                )
            } else {
                Toast.makeText(context, R.string.fill_all_required_fields, Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvToLogin.setOnClickListener {
            viewModel.navController.navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navController = findNavController()

        binding.inputEmail.doAfterTextChanged {
            viewModel.inputEmailTextChanged(it.toString())
        }

        binding.inputPassword.doAfterTextChanged {
            viewModel.inputPasswordTextChanged(it.toString())
        }

        binding.inputPasswordConfirm.doAfterTextChanged {
            viewModel.inputPasswordConfirmTextChanged(
                    binding.inputPassword.text.toString(),
                    it.toString())
        }

        viewModel.isValidEmail.observe(viewLifecycleOwner) { isValid ->
            if (!isValid) {
                binding.inputEmail.error = getString(R.string.incorrect_email)
            }
        }

        viewModel.isValidPassword.observe(viewLifecycleOwner) { isValid ->
            if (!isValid) {
                binding.inputPassword.error = getString(R.string.short_password)
            }
        }

        viewModel.isValidPasswordConfirm.observe(viewLifecycleOwner) { isValid ->
            if (!isValid) {
                binding.inputPasswordConfirm.error = getString(R.string.different_password)
            }
        }

        viewModel.showError.observe(viewLifecycleOwner) {
            Toast.makeText(context, getString(R.string.smth_wrong), Toast.LENGTH_SHORT).show()
        }
    }
}