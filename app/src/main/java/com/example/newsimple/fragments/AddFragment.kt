package com.example.newsimple.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsimple.Functions
import com.example.newsimple.R
import com.example.newsimple.UserViewModel
import com.example.newsimple.entities.Note
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private  var func = Functions()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.butAdd.setOnClickListener {
            insertDataToDatabase()
        }
        return view

    }

    private fun insertDataToDatabase() {
        val title = editInputText.text.toString()
        val desc = editInputDesc.text.toString()
        val prioryEdit = editInputPriori.text

        if (func.inputCheck(title, desc, prioryEdit)) {
            val note = Note(0, title, desc, Integer.parseInt(prioryEdit.toString()))
            viewModel.addNote(note)
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "Please fill out", Toast.LENGTH_SHORT).show()
        }
    }
}