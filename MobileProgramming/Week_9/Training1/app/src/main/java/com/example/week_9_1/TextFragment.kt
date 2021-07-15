package com.example.week_9_1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.week_9_1.databinding.FragmentTextBinding


class TextFragment : Fragment() {
    var binding:FragmentTextBinding?=null
    val myViewModel : MyViewModel by activityViewModels()
    val data = arrayListOf<String>("ImageData1","ImageData2","ImageData3")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTextBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = requireActivity().intent
        val imgNum = intent.getIntExtra("imgNum", -1)
        if(imgNum!=-1){
            binding!!.textView.text = data[imgNum]
        }
        else{
            myViewModel.selectednum.observe(viewLifecycleOwner, Observer {
                binding!!.textView.text = data[it]
            })
        }
    }

}
