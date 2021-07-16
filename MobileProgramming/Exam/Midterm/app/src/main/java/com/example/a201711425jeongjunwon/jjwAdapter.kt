package com.example.a201711425jeongjunwon

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class jjwAdapter(var items:ArrayList<jjwData>):RecyclerView.Adapter<jjwAdapter.ViewHolder>() {
    interface OnItemClickListener{
        fun OnItemClick(holder: RecyclerView.ViewHolder, view:View, data:jjwData, position:Int)
    }

    var itemClickListener:OnItemClickListener?=null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemNameView = itemView.findViewById<TextView>(R.id.itemNameView)
        val itemPriceView = itemView.findViewById<TextView>(R.id.itemPriceView)
        init{
            itemView.setOnClickListener {
                itemClickListener?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.jjwtest1row, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemNameView.text = items[position].name
        holder.itemPriceView.text = items[position].price.toString()
    }
}