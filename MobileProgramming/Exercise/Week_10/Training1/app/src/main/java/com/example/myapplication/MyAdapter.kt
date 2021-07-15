package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RowBinding

class MyAdapter(val item:ArrayList<MyData>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    interface OnItemClickListener{
        fun onItemClicked(holder:MyViewHolder, view:View, data:MyData, position:Int)
    }

    var itemClickListener:OnItemClickListener ?= null

    inner class MyViewHolder(val binding:RowBinding) : RecyclerView.ViewHolder(binding.root){
        init{
            binding.textView.setOnClickListener {
                itemClickListener?.onItemClicked(this, it, item[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.textView.text = item[position].newstitle
    }
}