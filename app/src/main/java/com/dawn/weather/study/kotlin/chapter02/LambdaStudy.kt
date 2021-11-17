package com.dawn.weather.study.kotlin.chapter02

/**
 *  @copyright: 2019
 *  @project KotlinStudy
 *  @author LuoXuLiang
 *  @description:  description
 *  @date: 2021/11/9 14:17
 */

fun main() {
    //mutableListOf()函数创建一个可变的集合
    val mutableList = mutableListOf("apple", "orange", "Banana", "watermelon", "789", "最长单词")
    mutableList.add("666")
    for (afterList in mutableList) {
        println(afterList)
    }

    val maxByOrNull = mutableList.maxByOrNull { it.length }
    println("maxByOrNull-->$maxByOrNull")

    val list1 = mutableList.filter { it.length > 5 }.map { it.uppercase() }
    println(list1)

    //any函数用于判断集合中是否至少存在一个元素满足指定条件
    val any = mutableList.any { it.length > 5 }
    println("any-->$any")

    //all函数用于判断集合中是否所有元素都满足指定条件
    val all = mutableList.all { it.length > 5 }
    println("all-->$all")

    Thread(object : Runnable {
        override fun run() {
            println("Runnable")
        }

    }).start()

    //简写
    Thread { println("简写 Runnable") }.start()

    doStudy(Person())
    doStudy(null)

    val a = "123"
    val b: Int = 0
    val c = if (a != null) {
        a
    } else {
        b
    }

    //?: 如果左边表达式的结果不为空就返回左边表达式的结果，否则就返回右边表达式的结果
    val f = a ?: b
}

/**
 * ?可为空
 * ?.判空  等于 if（person != null）
 * ?: 如果左边表达式的结果不为空就返回左边表达式的结果，否则就返回右边表达式的结果
 */
fun doStudy(person: Person?) {
    person?.eat()
}

/**
 * ?:
 */
fun getTextLength(text: String?) = text?.length ?: 0

