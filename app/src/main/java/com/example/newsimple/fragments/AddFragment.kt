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
import com.example.newsimple.R
import com.example.newsimple.UserViewModel
import com.example.newsimple.entities.User
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {
    private lateinit var viewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.butAdd.setOnClickListener {
            insertDataToDatabase()
        }
        view.butDelete.setOnClickListener {
            deleteNote()
        }
        return view

    }

    private fun deleteNote() {
        val nameNote = editInputText.text.toString()
        val descNote = editInputDesc.text.toString()
        val prioryNote = editInputPriori.text
        if (nameNote.isNotEmpty() && descNote.isNotEmpty() && prioryNote.isNotEmpty()) {
            val note = User(0, nameNote, descNote, Integer.parseInt(prioryNote.toString()))
            viewModel.deleteUser(note)
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
    }

    private fun insertDataToDatabase() {
        val nameNote = editInputText.text.toString()
        val descNote = editInputDesc.text.toString()
        val prioryNote = editInputPriori.text

        if (inputCheck(nameNote, descNote, prioryNote)) {
            val note = User(0, nameNote, descNote, Integer.parseInt(prioryNote.toString()))
            viewModel.addUsers(note)
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

            Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(requireContext(), "Please fill out", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(nameNote: String, nameDesc: String, priory: Editable): Boolean {
        return !(TextUtils.isEmpty(nameNote) && TextUtils.isEmpty(nameDesc) && priory.isEmpty())
    }

}