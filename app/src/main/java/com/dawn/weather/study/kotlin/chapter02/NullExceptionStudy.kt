package com.dawn.weather.study.kotlin.chapter02

/**
 *  @copyright: 2019
 *  @project KotlinStudy
 *  @author LuoXuLiang
 *  @description:  空指针异常
 *  @date: 2021/11/9 15:12
 */

var content: String? = "hello"

fun main() {
    if (content != null) {
        printUpperCase()
    }

    doStudyTwo(Person())
    doStudyTwo(null)

    val a = "123"
    val b: Int = 0
    val c = if (a != null) {
        a
    } else {
        b
    }

    //?: 如果左边表达式的结果不为空就返回左边表达式的结果，否则就返回右边表达式的结果
    val f = a ?: b

    test("罗序良",sex = "男")
}

fun printUpperCase() {
//    val upperCase = content.uppercase()   //编译器检查不出这句不为空
    /**
     * 用非空断言工具，写法是在对象的后面加上!!
     */
    val upperCase = content!!.uppercase()
    println(upperCase)
}




/**
 * ?可为空
 * ?.判空  等于 if（person != null）
 * ?: 如果左边表达式的结果不为空就返回左边表达式的结果，否则就返回右边表达式的结果
 */
fun doStudyTwo(person: Person?) {
    //person?.eat()

    person?.let {
        it.eat()
        println("let")
    }
}

/**
 * ?:
 */
fun getTextLengthTwo(text: String?) = text?.length ?: 0

/**
 *
 */
fun test(name:String, sex : String = "man"){
    println("$name,$sex")
}

