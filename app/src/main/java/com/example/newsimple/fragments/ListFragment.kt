package com.example.newsimple.fragments

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsimple.R
import com.example.newsimple.adapters.UserAdapter
import com.example.newsimple.models.UserViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    val adapterRc = UserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView(view)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        viewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
            adapterRc.setData(note)
        })
        imageFilter.setOnClickListener {
            viewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
                adapterRc.setData(note)
            })
        }

        filterHigh.setOnClickListener {
            viewModel.readHighNotes().observe(viewLifecycleOwner, Observer { note ->
                adapterRc.setData(note)
            })
        }
        filterMiddle.setOnClickListener {
            viewModel.readMiddleNotes().observe(viewLifecycleOwner, Observer { note ->
                adapterRc.setData(note)
            })
        }
        filterLow.setOnClickListener {
            viewModel.readLowNotes().observe(viewLifecycleOwner, Observer { note ->
                adapterRc.setData(note)
            })
        }
        view.floatActionB.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_listFragment_to_addFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }


    private fun setupRecyclerView(view: View) {
        rcView.apply {
            adapter = adapterRc
            layoutManager = GridLayoutManager(
                requireContext(), 2
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
//        val item = menu.findItem(R.id.search_button)
//        val searchView = item.actionView as SearchView
//        searchView.queryHint = "Введите название заметки"
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//                return true
//            }
//
//        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteAllButton) {
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
        return super.onOptionsItemSelected(item)
    }

}