package com.example.newsimple.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsimple.R
import com.example.newsimple.diffutills.MyDiffCallBack
import com.example.newsimple.entities.Note
import com.example.newsimple.fragments.AddFragment.Companion.HIGH_PRIORITY
import com.example.newsimple.fragments.AddFragment.Companion.LOW_PRIORITY
import com.example.newsimple.fragments.AddFragment.Companion.MID_PRIORITY
import com.example.newsimple.fragments.ListFragmentDirections
import kotlinx.android.synthetic.main.item_rc.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    var oldList = emptyList<Note>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textNote = view.findViewById<TextView>(R.id.textItemName)
        var colorPriory = view.findViewById<ImageView>(R.id.color_priory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val noteItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rc, parent, false)
        return ViewHolder(noteItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemNote = oldList[position]
        holder.textNote.text = itemNote.noteTitle
        when (itemNote.priory) {
            HIGH_PRIORITY -> {
                holder.colorPriory.setBackgroundResource(R.drawable.red_oval)
            }
            LOW_PRIORITY -> {
                holder.colorPriory.setBackgroundResource(R.drawable.green_oval)
            }
            MID_PRIORITY -> {
                holder.colorPriory.setBackgroundResource(R.drawable.yellow_oval)
            }
        }
        holder.itemView.row_layout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToEditFragment(itemNote)
            holder.itemView.findNavController().navigate(action)
        }
    }
    fun setData(list: List<Note>){
        val callback = MyDiffCallBack(oldList,list)
        val result = DiffUtil.calculateDiff(callback)
        result.dispatchUpdatesTo(this)
        oldList = list
    }

    override fun getItemCount(): Int {
        return oldList.size
    }
}

