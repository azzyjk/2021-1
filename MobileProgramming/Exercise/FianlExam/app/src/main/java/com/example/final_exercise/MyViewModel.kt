 package com.example.final_exercise

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    var cafes = MutableLiveData<MutableList<CafeInfo>>()
    var selectedNum = MutableLiveData<Int>()

    init{
        cafes.value = mutableListOf<CafeInfo>()
    }

    fun addCafe(cafe:CafeInfo){
        cafes.value?.add(cafe)
    }

    fun setSelectedNum(num:Int){
        selectedNum.value = num
    }

}