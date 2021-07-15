package com.example.week_7_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week_7_1.databinding.RowBinding

class MyAdapter(val items:ArrayList<MyData>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    interface OnItemClickListener{
        public fun onItemClick(holder: RecyclerView.ViewHolder, view:View, data:MyData, position:Int)
    }

    var listener:OnItemClickListener ?= null

    inner class ViewHolder(val binding: RowBinding): RecyclerView.ViewHolder(binding.root) {

        init{
            binding.root.setOnClickListener {
                listener?.onItemClick(this, it, items[adapterPosition], adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply{
            appclass.text = items[position].appclass
            applabel.text = items[position].applabel
            imageView.setImageDrawable(items[position].appicon)
        }
    }
}