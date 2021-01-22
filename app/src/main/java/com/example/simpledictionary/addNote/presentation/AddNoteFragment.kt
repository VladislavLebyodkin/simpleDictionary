package com.example.simpledictionary.addNote.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simpledictionary.R
import com.example.simpledictionary.databinding.AddNoteFragmentBinding
import com.example.simpledictionary.util.isNotEmptyField
import com.example.simpledictionary.util.validate
import org.koin.android.viewmodel.ext.android.viewModel

class AddNoteFragment : Fragment() {

    private val viewModel: AddNoteViewModel by viewModel()
    private lateinit var binding: AddNoteFragmentBinding

    private var isValidName = false
    private var isValidTranslate = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = AddNoteFragmentBinding.inflate(layoutInflater, container, false)

        binding.btnSubmit.setOnClickListener {
            if (isValidName && isValidTranslate) {
                viewModel.createNote(
                        binding.inputName.text.toString(),
                        binding.inputTranslate.text.toString(),
                        binding.inputExample.text.toString()
                )
            } else {
                Toast.makeText(context, getString(R.string.fill_all_required_fields), Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navController = findNavController()

        binding.inputName.validate(getString(R.string.input_word)) { field ->
            isValidName = field.isNotEmptyField()
            field.isNotEmptyField()
        }

        binding.inputTranslate.validate(getString(R.string.input_translate)) { field ->
            isValidTranslate = field.isNotEmptyField()
            field.isNotEmptyField()
        }
    }
}