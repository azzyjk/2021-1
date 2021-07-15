package com.example.week_12_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week_12_1.databinding.RowBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class MyProductAdapter(options: FirebaseRecyclerOptions<Product>):FirebaseRecyclerAdapter<Product, MyProductAdapter.ViewHolder>(options) {

    interface OnItemClickListener{
        fun OnItemClick(view: View, position: Int)
    }

    var itemClickListener : OnItemClickListener ?= null

    inner class ViewHolder(val binding:RowBinding):RecyclerView.ViewHolder(binding.root){
        init{
            binding.root.setOnClickListener {
                itemClickListener!!.OnItemClick(it, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Product) {
        holder.binding.apply{
            productId.text = model.pId.toString()
            productName.text = model.pName.toString()
            productQuantity.text = model.pQuantity.toString()
        }


    }

}