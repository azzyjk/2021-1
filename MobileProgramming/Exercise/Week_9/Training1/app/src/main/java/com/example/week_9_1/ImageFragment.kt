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
    var binding:FragmentImageBinding ?= null
    val myViewModel:MyViewModel by activityViewModels()
    val imgArr : ArrayList<Int> = arrayListOf(R.drawable.welsh_1, R.drawable.welsh_2, R.drawable.welsh_3)

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
            imageView.setOnClickListener {
                if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                    val intent = Intent(activity, SecondActivity::class.java)
                    intent.putExtra("imgNum", myViewModel.selectedNum.value)
                    startActivity(intent)
                }
            }

            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when(checkedId){
                    R.id.imgBtn1-> myViewModel.setLiveData(0)
                    R.id.imgBtn2-> myViewModel.setLiveData(1)
                    R.id.imgBtn3-> myViewModel.setLiveData(2)
                }
                imageView.setImageResource(imgArr[myViewModel.selectedNum.value!!])
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}