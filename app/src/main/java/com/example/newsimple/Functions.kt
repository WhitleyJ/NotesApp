package com.example.newsimple

import com.example.newsimple.entities.Note
import com.example.newsimple.fragments.ListFragmentDirections

class Functions {


    fun navDirections(itemNote: Note) =
        ListFragmentDirections.actionListFragmentToEditFragment(itemNote)

}
