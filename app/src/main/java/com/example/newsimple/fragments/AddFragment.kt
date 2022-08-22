package com.example.newsimple.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsimple.R
import com.example.newsimple.entities.Note
import com.example.newsimple.models.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private var priority: String = LOW_PRIORITY

    override

    fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.butAdd.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        green_item.setOnClickListener {
            priority = LOW_PRIORITY
            green_item.setImageResource(R.drawable.ic_baseline_check_24)
            red_item.setImageResource(0)
            yellow_item.setImageResource(0)
        }
        red_item?.setOnClickListener {
            priority = HIGH_PRIORITY
            red_item.setImageResource(R.drawable.ic_baseline_check_24)
            yellow_item.setImageResource(0)
            green_item.setImageResource(0)
        }
        yellow_item.setOnClickListener {
            priority = MID_PRIORITY
            yellow_item.setImageResource(R.drawable.ic_baseline_check_24)
            red_item.setImageResource(0)
            green_item.setImageResource(0)
        }

        super.onViewCreated(view, savedInstanceState)
    }


    private fun insertDataToDatabase() {
        val title = editInputText.text.toString()
        val desc = editInputDesc.text.toString()


        val note = Note(0, title, desc, priority)
        viewModel.addNote(note)
        findNavController().navigate(R.id.action_addFragment_to_listFragment)


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val HIGH_PRIORITY = "1"
        const val MID_PRIORITY = "2"
        const val LOW_PRIORITY = "3"
    }
}