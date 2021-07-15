package com.example.final_exercise

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels


class ItemFragment : Fragment() {
    val myViewModel:MyViewModel by activityViewModels()
    lateinit var myAdapter:MyItemRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            myAdapter =  MyItemRecyclerViewAdapter(myViewModel.cafes.value!!)
            myAdapter.itemClickListener = object:MyItemRecyclerViewAdapter.OnItemClickListener{
                override fun onItemClicked(
                    holder: MyItemRecyclerViewAdapter.ViewHolder,
                    view: View,
                    data: CafeInfo,
                    position: Int
                ) {
                    myViewModel.setSelectedNum(position)
                    val context = activity as MainActivity
                    val fragmentTransaction = context.supportFragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack("1")
                    fragmentTransaction.replace(R.id.frameLayout, MapFragment())
                    fragmentTransaction.commit()
                }

            }

            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = myAdapter
            }
        }
        return view
    }

}