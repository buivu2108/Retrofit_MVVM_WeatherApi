package com.example.weatherapp.screens.current

class TestTwo<T> {


    var nunAble: T? = null

    var nonNun: Int = 5



    fun<K> getName(age: K) : K{

    }

    fun main() {
        nunAble?.let {
            getName(it)
        }

        val temp = nunAble
        if(temp != null){
            getName(temp)
        }




        val testTwo = TestTwo<CurrentViewModel>()

        for(i in 0..100){
            for(j in 0..100){
                if(j == 4){
                    return
                }
                print("ABC")
            }

        }
    }

    val name: T? = null

    fun<K> one(id:K) : K{
        val name: K
    }

    fun two() {

        return
    }
    fun three(age:Int) {
        val a:String=""
        return
    }

    fun getAge(age: Int) : String{
        return "Ban ${age} tuoi"
    }
}

