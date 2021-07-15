package com.example.midtermtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var items:ArrayList<MyData>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    interface OnItemClickListener{
        public fun OnItemClick(holder:RecyclerView.ViewHolder, view:View, data:MyData, position:Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameView = itemView.findViewById<TextView>(R.id.nameView)
        val menuView = itemView.findViewById<TextView>(R.id.menuView)
        val numberView = itemView.findViewById<TextView>(R.id.numberView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameView.text = items[position].name
        holder.menuView.text = items[position].menu
        holder.numberView.text = items[position].number
    }
}