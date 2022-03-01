package com.example.weatherapp.screens.future

import com.example.weatherapp.screens.current.TestTwo
import com.example.weatherapp.screens.model.Weather7Day

//generic

//generic trong java

//null va non null

//do phan giai, mat do diem anh(dp trong android)
//sp khac dp ntn
//khi khai bao bang sp
//text size 10sp
//dpi cua may


open class Parent(){
    fun showName(){

    }
}

class Child : Parent(){

}

class MyList<T : Parent> {
    val abc: T? = null
    //tham so cua ham
    //kieu tra ve cua ham

   fun add(item: T) : T{
       item.showName()
   }

}

fun main() {

    val test = TestTwo<String>()

    test.getName<Int>()
}