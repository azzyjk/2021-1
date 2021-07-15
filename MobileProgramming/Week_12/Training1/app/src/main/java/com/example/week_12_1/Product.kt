package com.example.week_12_1

data class Product(var pId:Int, var pName:String, var pQuantity:Int) {
    constructor():this(0, "noinfo", 0)
}