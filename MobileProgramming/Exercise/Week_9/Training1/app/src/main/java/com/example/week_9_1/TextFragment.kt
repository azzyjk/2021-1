package com.example.week_9_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.week_9_1.databinding.FragmentTextBinding

class TextFragment : Fragment() {
    var binding:FragmentTextBinding ?= null
    val myViewModel:MyViewModel by activityViewModels()
    val dataArr = arrayListOf("ImageData 1", "ImageData 2", "ImageData 3")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTextBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = requireActivity().intent
        val imgNum = intent.getIntExtra("imgNum", -1)

        if(imgNum != -1) binding!!.textView.text = dataArr[imgNum]
        else{
            myViewModel.selectedNum.observe(viewLifecycleOwner, Observer {
                binding!!.textView.text = dataArr[it]
            })
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}