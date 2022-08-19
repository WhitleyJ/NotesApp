package com.example.newsimple.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newsimple.Functions
import com.example.newsimple.R
import com.example.newsimple.UserViewModel
import com.example.newsimple.entities.Note
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_edit.view.*


class EditFragment : Fragment() {
    private val args by navArgs<EditFragmentArgs>()
    private lateinit var viewModel: UserViewModel
    private  var func = Functions()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit, container, false)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        getTextFromArgs(view)

        view.butUpdate.setOnClickListener {
            updateNote()
        }
        return view
    }

    private fun getTextFromArgs(view: View) {
        view.editGetName.setText(args.currentNote.noteTitle)
        view.editGetDesc.setText(args.currentNote.noteDesc)
        view.editGetPriory.setText(args.currentNote.priory.toString())
    }

    private fun updateNote() {
        val title = editGetName.text.toString()
        val desc = editGetDesc.text.toString()
        val prioryEdit = Integer.parseInt(editGetPriory.text.toString())

        if (func.inputCheck(title, desc, editGetPriory.text)) {
            val updateNote = Note(args.currentNote.id, title, desc, prioryEdit)
            viewModel.updateNote(updateNote)
            findNavController().navigate(R.id.action_editFragment_to_listFragment)
        }
    }
}