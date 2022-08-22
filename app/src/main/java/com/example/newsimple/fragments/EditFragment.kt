package com.example.newsimple.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newsimple.R
import com.example.newsimple.entities.Note
import com.example.newsimple.models.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_edit.view.*


class EditFragment : Fragment() {
    private val args by navArgs<EditFragmentArgs>()
    private lateinit var viewModel: UserViewModel
    private var priority: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit, container, false)
        setHasOptionsMenu(true)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        getTextFromArgs(view)


        view.butUpdate.setOnClickListener {
            updateNote()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        green_item.setOnClickListener {
            priority = AddFragment.LOW_PRIORITY
            green_item.setImageResource(R.drawable.ic_baseline_check_24)
            red_item.setImageResource(0)
            yellow_item.setImageResource(0)
        }
        red_item?.setOnClickListener {
            priority = AddFragment.HIGH_PRIORITY
            red_item.setImageResource(R.drawable.ic_baseline_check_24)
            yellow_item.setImageResource(0)
            green_item.setImageResource(0)
        }
        yellow_item.setOnClickListener {
            priority = AddFragment.MID_PRIORITY
            yellow_item.setImageResource(R.drawable.ic_baseline_check_24)
            red_item.setImageResource(0)
            green_item.setImageResource(0)
        }
        super.onViewCreated(view, savedInstanceState)
    }


    private fun getTextFromArgs(view: View) {
        view.editGetName.setText(args.currentNote.noteTitle)
        view.editGetDesc.setText(args.currentNote.noteDesc)
        yellow_item.setImageResource(args.currentNote.priory.toInt())
        red_item.setImageResource(args.currentNote.priory.toInt())
        green_item.setImageResource(args.currentNote.priory.toInt())


    }

    private fun updateNote() {
        val title = editGetName.text.toString()
        val desc = editGetDesc.text.toString()

        val updateNote = Note(args.currentNote.id, title, desc, args.currentNote.priory)
        viewModel.updateNote(updateNote)
        findNavController().navigate(R.id.action_editFragment_to_listFragment)

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.onBackPressed()
            return true
        }
        if (item.itemId == R.id.menu_delete) {
            viewModel.deleteNote(args.currentNote)
            findNavController().navigate(R.id.action_editFragment_to_listFragment)
        }
        return super.onOptionsItemSelected(item)
    }

}