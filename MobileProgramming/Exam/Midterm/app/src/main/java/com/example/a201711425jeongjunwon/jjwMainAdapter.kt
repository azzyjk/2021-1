package com.example.a201711425jeongjunwon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class jjwMainAdapter(var items:ArrayList<jjwData>): RecyclerView.Adapter<jjwMainAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemMainName = itemView.findViewById<TextView>(R.id.mainItemNameView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): jjwMainAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.jjwmainrow, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemMainName.text = items[position].name
    }

}