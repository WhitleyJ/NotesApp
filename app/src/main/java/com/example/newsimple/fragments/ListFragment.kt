package com.example.newsimple.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsimple.R
import com.example.newsimple.adapters.UserAdapter
import com.example.newsimple.entities.Note
import com.example.newsimple.models.UserViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    val adapterRc = UserAdapter()
    var oldMyNotes = arrayListOf<Note>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView(view)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        viewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
            oldMyNotes = note as ArrayList<Note>
            adapterRc.setData(note)
        })
        imageFilter.setOnClickListener {
            viewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
                oldMyNotes = note as ArrayList<Note>
                adapterRc.setData(note)
            })
        }

        filterHigh.setOnClickListener {
            viewModel.readHighNotes().observe(viewLifecycleOwner, Observer { note ->
                oldMyNotes = note as ArrayList<Note>
                adapterRc.setData(note)
            })
        }
        filterMiddle.setOnClickListener {
            viewModel.readMiddleNotes().observe(viewLifecycleOwner, Observer { note ->
                oldMyNotes = note as ArrayList<Note>
                adapterRc.setData(note)
            })
        }
        filterLow.setOnClickListener {
            viewModel.readLowNotes().observe(viewLifecycleOwner, Observer { note ->
                oldMyNotes = note as ArrayList<Note>
                adapterRc.setData(note)
            })
        }
        view.buttonDeleteAll.setOnClickListener {
            val buttonSheet: BottomSheetDialog = BottomSheetDialog(requireContext())
            buttonSheet.setContentView(R.layout.dialog_all)
            buttonSheet.show()
            val dialogYesAll = buttonSheet.findViewById<TextView>(R.id.answerYesAll)
            val dialogNoAll = buttonSheet.findViewById<TextView>(R.id.answerNoAll)
            dialogYesAll!!.setOnClickListener {
                viewModel.deleteAllNotes()
                buttonSheet.onBackPressed()
            }
            dialogNoAll!!.setOnClickListener {
                buttonSheet.onBackPressed()
            }
        }
        view.floatActionB.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_listFragment_to_addFragment)
        }
        searchText(view)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun searchText(view: View) {
        val searchView = view.findViewById<androidx.appcompat.widget.SearchView>(R.id.search_list)
        searchView.queryHint = "Введите название заметки"
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                filterText(p0)
                return false
            }
        })
    }

    private fun filterText(p0: String?) {
        val newFilteredList = arrayListOf<Note>()
        for(i in oldMyNotes){
            if(i.noteTitle.contains(p0!!)){
                newFilteredList.add(i)
            }
        }
        adapterRc.setData(newFilteredList)
    }

    private fun setupRecyclerView(view: View) {
        rcView.apply {
            adapter = adapterRc
            layoutManager = GridLayoutManager(
                requireContext(), 2
            )
        }
    }
}