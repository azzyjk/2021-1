package com.example.week_9_1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel(){
    val selectednum = MutableLiveData<Int>()

    fun setLiveData(num:Int){
        selectednum.value = num
    }
}