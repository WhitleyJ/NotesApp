package com.example.newsimple

import android.text.Editable
import android.text.TextUtils
import com.example.newsimple.entities.Note
import com.example.newsimple.fragments.ListFragmentDirections

class Functions {

    fun inputCheck(title: String, desc: String, prioryEdit: Editable): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(desc) && prioryEdit.isEmpty())
    }
    fun navDirections(itemNote: Note) =
        ListFragmentDirections.actionListFragmentToEditFragment(itemNote)
}