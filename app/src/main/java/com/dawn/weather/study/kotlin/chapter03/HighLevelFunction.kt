package com.dawn.weather.study.kotlin.chapter03

/**
 *  @author: LXL
 *  @description: 高阶函数
 *  @date: 2021/11/12 14:08
 *  高阶函数的定义:
 *  如果一个函数接收另一个函数作为参数，
 *  或者返回值的类型是另一个函数，那么该函数就称为高阶函数。
 *  (String, Int) -> Unit
 */
class HighLevelFunction {
}

fun main() {
    val num1 = 100
    val num2 = 11
    //第三个参数使用了::plus和::minus这种写法。这是一种函数引用方式的写法，
    // 表示将plus()和minus()函数作为参数传递给num1AndNum2()函数
    /* val num1Result = num1AndNum2(num1,num2,::plus)
     val num2Result = num1AndNum2(num1,num2,::minus)*/

    val num1Result = num1AndNum2(num1, num2) { n1, n2 ->
        n1 + n2
    }

    val num2Result = num1AndNum2(num1, num2) { n1, n2 ->
        n1 - n2
    }
    println(num1Result)
    println(num2Result)

}

/**
 * 高阶函数示例
 */
fun example(func: (String, Int) -> Unit) {
    func("hello", 123)
}

//高阶函数
fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    return operation(num1, num2)
}

fun plus(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun minus(num1: Int, num2: Int): Int {
    return num1 - num2
}