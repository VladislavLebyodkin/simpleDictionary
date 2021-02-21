package com.vlados_project.simpledictionary.login.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vlados_project.simpledictionary.R
import com.vlados_project.simpledictionary.databinding.LoginFragmentBinding
import com.vlados_project.simpledictionary.util.log
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()
    lateinit var binding: LoginFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = LoginFragmentBinding.inflate(layoutInflater, container, false)

        binding.btnSubmitLogin.setOnClickListener {
            if(viewModel.isValidEmail.value == true && viewModel.isValidPassword.value == true) {
                viewModel.onLoginButtonClick(
                        binding.inputEmail.editText?.text.toString(),
                        binding.inputPassword.editText?.text.toString()
                )
            } else {
                Toast.makeText(context, R.string.fill_all_required_fields, Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvToRegister.setOnClickListener {
            viewModel.navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navController = findNavController()

        startValidation()

        binding.inputEmail.editText?.doAfterTextChanged {
            viewModel.inputEmailTextChanged(it.toString())
        }

        binding.inputPassword.editText?.doAfterTextChanged {
            viewModel.inputPasswordTextChanged(it.toString())
        }

        viewModel.isValidEmail.observe(viewLifecycleOwner) { isValid ->
            if (!isValid) {
                binding.inputEmail.error = getString(R.string.incorrect_email)
            } else {
                binding.inputPassword.error = null
            }
        }

        viewModel.isValidPassword.observe(viewLifecycleOwner) { isValid ->
            if (!isValid) {
                binding.inputPassword.error = getString(R.string.short_password)
            } else {
                binding.inputPassword.error = null
            }
        }

        viewModel.showError.observe(viewLifecycleOwner) {
            Toast.makeText(context, getString(R.string.smth_wrong), Toast.LENGTH_SHORT).show()
        }
    }

    private fun startValidation() {
        viewModel.inputEmailTextChanged(binding.inputEmail.editText?.text.toString())
        viewModel.inputPasswordTextChanged(binding.inputPassword.editText?.text.toString())
    }
}