package com.example.newsimple.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsimple.R
import com.example.newsimple.adapters.UserAdapter
import com.example.newsimple.models.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.item_rc.view.*


class ListFragment : Fragment() {

    lateinit var viewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        setupRecyclerView(view)


        view.floatActionB.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_listFragment_to_addFragment)
        }
        return view
    }


    private fun setupRecyclerView(view: View) {
        val rcView = view.rcView
        val adapterRc = UserAdapter()
        rcView.apply {
            adapter = adapterRc
            layoutManager = GridLayoutManager(
                context, 2,GridLayoutManager.VERTICAL, false
            )
        }

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
            adapterRc.setData(note)
        })

    }

}