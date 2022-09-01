package com.example.newsimple.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newsimple.R
import com.example.newsimple.entities.Note
import com.example.newsimple.fragments.AddFragment.Companion.HIGH_PRIORITY
import com.example.newsimple.fragments.AddFragment.Companion.LOW_PRIORITY
import com.example.newsimple.fragments.AddFragment.Companion.MID_PRIORITY
import com.example.newsimple.models.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_edit.view.*


class EditFragment : Fragment() {
    private val args by navArgs<EditFragmentArgs>()
    private lateinit var viewModel: UserViewModel
    private var priority: String = LOW_PRIORITY


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit, container, false)
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val butUpdateList = view.findViewById<FloatingActionButton>(R.id.butUpdate)
        view.editGetName.setText(args.currentNote.noteTitle)
        view.editGetDesc.setText(args.currentNote.noteDesc)

        when (args.currentNote.priory) {
            HIGH_PRIORITY -> {
                view.red_item2.setImageResource(R.drawable.ic_baseline_check_24)
            }
            MID_PRIORITY -> {
                view.yellow_item2.setImageResource(R.drawable.ic_baseline_check_24)

            }
            LOW_PRIORITY -> {
                view.green_item2.setImageResource(R.drawable.ic_baseline_check_24)
            }
        }
        green_item2.setOnClickListener {
            priority = LOW_PRIORITY
            view.green_item2.setImageResource(R.drawable.ic_baseline_check_24)
            view.red_item2.setImageResource(0)
            view.yellow_item2.setImageResource(0)
        }
        red_item2.setOnClickListener {
            priority = HIGH_PRIORITY
            view.red_item2.setImageResource(R.drawable.ic_baseline_check_24)
            view.yellow_item2.setImageResource(0)
            view.green_item2.setImageResource(0)
        }
        yellow_item2.setOnClickListener {
            priority = MID_PRIORITY
            view.yellow_item2.setImageResource(R.drawable.ic_baseline_check_24)
            view.red_item2.setImageResource(0)
            view.green_item2.setImageResource(0)
        }
        butUpdateList.setOnClickListener {
            updateNote()
        }
        super.onViewCreated(view, savedInstanceState)
    }


    private fun updateNote() {
        val title = editGetName.text.toString()
        val desc = editGetDesc.text.toString()

        val note = Note(args.currentNote.id, title, desc, priority)
        viewModel.updateNote(note)
        editToList()
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            editToList()
            return true
        }
        if (item.itemId == R.id.menu_delete) {
            viewModel.deleteNote(args.currentNote)
            editToList()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun editToList() {
        findNavController().navigate(R.id.action_editFragment_to_listFragment)
    }

}