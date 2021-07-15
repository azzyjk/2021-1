package com.example.week_7_1

import android.graphics.drawable.Drawable
import java.io.Serializable

data class MyData(var applabel:String, var appclass : String, var apppackname:String, var appicon:Drawable):Serializable {
}