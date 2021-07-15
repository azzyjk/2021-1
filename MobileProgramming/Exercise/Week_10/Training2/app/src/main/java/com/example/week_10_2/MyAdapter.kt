package com.example.week_10_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week_10_2.databinding.RowBinding

class MyAdapter(val items:ArrayList<MyData>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    interface OnItemClickListener{
        fun onItemClicked(holder:MyViewHolder, view: View, data:MyData, position: Int)
    }

    var onitemClickListener:OnItemClickListener ?= null

    inner class MyViewHolder(val rowBinding: RowBinding):RecyclerView.ViewHolder(rowBinding.root){
        init{
            rowBinding.textView.setOnClickListener {
                onitemClickListener?.onItemClicked(this, it, items[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.rowBinding.textView.text = items[position].newsTitle
    }

}