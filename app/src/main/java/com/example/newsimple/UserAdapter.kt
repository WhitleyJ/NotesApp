package com.example.newsimple

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsimple.entities.Note
import kotlinx.android.synthetic.main.item_rc.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    var listNote = emptyList<Note>()


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textNote = view.findViewById<TextView>(R.id.textItemName)
        val textDesc = view.findViewById<TextView>(R.id.textItemDesc)
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
        holder.textDesc.text = itemNote.noteDesc
        holder.textPrior.text = itemNote.priory.toString()

        holder.itemView.row_layout.setOnClickListener {
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
