package com.dawn.weather.study.kotlin.chapter02

import java.lang.Integer.max

/**
 *  @copyright: 2019
 *  @project KotlinStudy
 *  @author LuoXuLiang
 *  @description:  description
 *  @date: 2021/11/6 15:42
 */
fun main() {
    var num = 1;
    val a: Int = 10
    println(a)

    println(largerNumber(22, 99))


    for (i in 10 downTo 0 step 2) {
        println(i)
    }

    val person = Person()
    person.name = "dawn"
    person.age = 19
    person.eat()

    val student = Student("LaLa", "666")
    student.name = "测试"
    student.age = 18
    student.eat()

    student.doHomework()
    student.writeWord()

}

fun largerNumber(num1: Int, num2: Int): Int {
    return max(num1, num2)
}

fun largerNumber2(num1: Int, num2: Int) = max(num1, num2)

fun largerNumberMax(num1: Int, num2: Int) = if (num1 > num2) num1 else num2

fun getScore(name: String) = when (name) {
    "Tom" -> {
        86
    }
    "Jim" -> 77
    "Jack" -> 95
    "Lily" -> 100
    else -> 0
}

fun getScore2(name: String) = when {
    name.startsWith("Tom") -> 86
    name == "Jim" -> 77
    name == "Jack" -> 95
    name == "Lily" -> 100
    else -> 0
}

