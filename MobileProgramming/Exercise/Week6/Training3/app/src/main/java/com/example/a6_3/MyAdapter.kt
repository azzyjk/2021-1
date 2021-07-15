package com.example.a6_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val items:ArrayList<MyData>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val wordView = itemView.findViewById<TextView>(R.id.wordView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.wordView.text = items[position].word
    }
}