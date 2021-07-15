package com.example.week_5_4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(val items:ArrayList<MyData>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    interface OnItemClickListener{
        fun OnItemClick(holder: RecyclerView.ViewHolder, view:View, data:MyData, position:Int)
    }

    var itemClickListener:OnItemClickListener?=null

    fun moveItem(oldPos:Int, newPos:Int){
        val item = items[oldPos]
        items.removeAt(oldPos)
        items.add(newPos, item)
        notifyItemMoved(oldPos, newPos)
    }

    fun removeItem(pos:Int){
        items.removeAt(pos)
        notifyItemRemoved(pos) // 얘네들은 어떤 아이템이 바뀌었는지가 아니라 그냥 notify만 해주면 되는지? 그렇다면 위치번호는 왜 넣는지
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var wordView:TextView = itemView.findViewById(R.id.wordView)
        var meanView:TextView = itemView.findViewById(R.id.meanView)
        init{
            itemView.setOnClickListener{
                itemClickListener?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
                if(meanView.visibility == View.VISIBLE){
                    items[adapterPosition].state = "GONE"
                    meanView.visibility = View.GONE
                }
                else if(meanView.visibility == View.GONE){
                    items[adapterPosition].state = "VISIBLE"
                    meanView.visibility = View.VISIBLE
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false) // root의 layout구조를 따르는지
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.wordView.text = items[position].word
        holder.meanView.text = items[position].meaning
        if(items[position].state == "GONE")
            holder.meanView.visibility = View.GONE
        else
            holder.meanView.visibility = View.VISIBLE
    }

}