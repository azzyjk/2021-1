package com.example.week_9_1

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.week_9_1.databinding.FragmentImageBinding

class ImageFragment : Fragment() {
    var binding:FragmentImageBinding?=null
    val myViewModel:MyViewModel by activityViewModels()
    val imglist = arrayListOf<Int>(R.drawable.welsh1, R.drawable.welsh2, R.drawable.welsh3)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.apply{
            imageView2.setOnClickListener {
                if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                    val i = Intent(activity, SecondActivity::class.java)
                    i.putExtra("imgNum", myViewModel.selectednum.value)
                    startActivity(i)
                }
            }
            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when(checkedId){
                    R.id.radioBtn1->{
                        myViewModel.setLiveData(0)
                    }
                    R.id.radioBtn2->{
                        myViewModel.setLiveData(1)
                    }
                    R.id.radioBtn3->{
                        myViewModel.setLiveData(2)
                    }
                }
                imageView2.setImageResource(imglist[myViewModel.selectednum.value!!])
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
