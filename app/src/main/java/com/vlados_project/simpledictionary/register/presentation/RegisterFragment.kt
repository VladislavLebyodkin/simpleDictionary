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
                        binding.inputEmail.editText?.text.toString(),
                        binding.inputPassword.editText?.text.toString(),
                        binding.inputPasswordConfirm.editText?.text.toString()
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

        viewModel.inputEmailTextChanged(binding.inputEmail.editText?.text.toString())
        viewModel.inputPasswordTextChanged(binding.inputPassword.editText?.text.toString())
        viewModel.inputPasswordConfirmTextChanged(
            binding.inputPassword.editText?.text.toString(),
            binding.inputPasswordConfirm.editText?.text.toString()
        )

        binding.inputEmail.editText?.doAfterTextChanged {
            viewModel.inputEmailTextChanged(it.toString())
        }

        binding.inputPassword.editText?.doAfterTextChanged {
            viewModel.inputPasswordTextChanged(it.toString())
        }

        binding.inputPasswordConfirm.editText?.doAfterTextChanged {
            viewModel.inputPasswordConfirmTextChanged(
                    binding.inputPassword.editText?.text.toString(),
                    it.toString())
        }

        viewModel.isValidEmail.observe(viewLifecycleOwner) { isValid ->
            if (!isValid) {
                binding.inputEmail.error = getString(R.string.incorrect_email)
            } else {
                binding.inputEmail.error = null
            }
        }

        viewModel.isValidPassword.observe(viewLifecycleOwner) { isValid ->
            if (!isValid) {
                binding.inputPassword.error = getString(R.string.short_password)
            } else {
                binding.inputPassword.error = null
            }
        }

        viewModel.isValidPasswordConfirm.observe(viewLifecycleOwner) { isValid ->
            if (!isValid) {
                binding.inputPasswordConfirm.error = getString(R.string.different_password)
            } else {
                binding.inputPasswordConfirm.error = null
            }
        }

        viewModel.showError.observe(viewLifecycleOwner) {
            Toast.makeText(context, getString(R.string.smth_wrong), Toast.LENGTH_SHORT).show()
        }
    }
}