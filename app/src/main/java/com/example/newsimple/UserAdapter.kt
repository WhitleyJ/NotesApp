package com.example.newsimple

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.newsimple.entities.Note
import com.example.newsimple.fragments.ListFragmentDirections
import kotlinx.android.synthetic.main.item_rc.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    var listNote = emptyList<Note>()
    private  var func = Functions()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textNote = view.findViewById<TextView>(R.id.textItemName)
        val textPrior = view.findViewById<TextView>(R.id.textItemPrior)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val noteItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rc, parent, false)
        return ViewHolder(noteItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemNote = listNote[position]

        holder.textNote.text = itemNote.noteTitle
        holder.textPrior.apply {
            text = itemNote.priory.toString()
        }

        holder.itemView.row_layout.setOnLongClickListener {

            true
        }

        holder.itemView.row_layout.setOnClickListener {
            val action = func.navDirections(itemNote)
            holder.itemView.findNavController().navigate(action)
        }
    }


    override fun getItemCount(): Int {
        return listNote.size
    }

    fun setData(note: List<Note>) {
        this.listNote = note
        notifyDataSetChanged()
    }
}

