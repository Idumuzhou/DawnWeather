package com.dawn.weather.study.kotlin.chapter02

/**
 *  @copyright: 2019
 *  @project KotlinStudy
 *  @author LuoXuLiang
 *  @description:  description
 *  @date: 2021/11/9 13:06
 */
fun main() {
    val cellphone = Cellphone(brand = "123456", price = 100.89)
    val cellphone2 = Cellphone(brand = "123456", price = 100.89)
    println(cellphone)
    println(cellphone == cellphone2)

    println(Singleton.singletonTest())

    //listOf()函数创建的是一个  **不可变的集合**
    val list = listOf("apple", "orange")
    for (lastList in list) {
        println(lastList)
    }

    //mutableListOf()函数创建一个可变的集合
    val mutableList = mutableListOf("apple", "orange", "Banana", "watermelon", "789", "最长单词")
    mutableList.add("666")
    for (afterList in mutableList) {
        println(afterList)
    }

    val maxByOrNull = mutableList.maxByOrNull { it.length }
    println("maxByOrNull$maxByOrNull")

    val list1 = mutableList.filter { it.length > 5 }.map { it.uppercase() }
    println(list1)

    //Set集合中是不可以存放重复元素的，如果存放了多个相同的元素，只会保留其中一份，这是和List集合最大的不同之处
    val set = setOf("1", "2", "3", "4", "2")
    for (afterSet in set) {
        println(afterSet)
    }

    //HashMap的实例
    val map = HashMap<String, Int>()
    map.put("Apple", 1)
    map["Orange"] = 2

    println(map["Apple"])  //取值

    //简化
    val mapOf = mapOf("Apple" to 1, "Orange" to 666)
    println(mapOf["Orange"])

    for ((name, value) in mapOf) {
        println("$name--$value")
    }


}