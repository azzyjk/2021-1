package com.example.a5_4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MyAdapter(val items:ArrayList<MyData>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    interface OnItemClickListener{
        fun OnItemClick(holder:RecyclerView.ViewHolder, view:View, data:MyData, position:Int)
    }

    public var listener:OnItemClickListener ?= null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val wordText:TextView = itemView.findViewById(R.id.wordView)
        val meanText:TextView = itemView.findViewById(R.id.meanView)
        init{
            itemView.setOnClickListener {
                listener?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
                if(meanText.visibility == View.VISIBLE){
                    items[adapterPosition].state = "GONE"
                    meanText.visibility = View.GONE
                }
                else{
                    items[adapterPosition].state = "VISIBLE"
                    meanText.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.wordText.text = items[position].word
        holder.meanText.text = items[position].mean
        if(items[position].state == "GONE"){
            holder.meanText.visibility = View.GONE
        }
        else{
            holder.meanText.visibility = View.VISIBLE
        }
    }

}