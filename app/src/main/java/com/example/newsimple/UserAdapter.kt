package com.example.newsimple

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsimple.entities.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    var listUser = emptyList<User>()

//    var longClick = User()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textNote = view.findViewById<TextView>(R.id.textItemName)
        val textDesc = view.findViewById<TextView>(R.id.textItemDesc)
        val textPrior = view.findViewById<TextView>(R.id.textItemPrior)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val noteItem = LayoutInflater.from(parent.context).inflate(R.layout.item_rc, parent, false)
        return ViewHolder(noteItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemNote = listUser[position]
        holder.textNote.text = itemNote.firstName
        holder.textDesc.text = itemNote.lastName
        holder.textPrior.text = itemNote.age.toString()
        holder.itemView.id = itemNote.id

//        holder.itemView.setOnLongClickListener {
////            longClick.invoke(listUser[position])
//        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    fun setData(user: List<User>) {
        this.listUser = user
        notifyDataSetChanged()
    }
}
