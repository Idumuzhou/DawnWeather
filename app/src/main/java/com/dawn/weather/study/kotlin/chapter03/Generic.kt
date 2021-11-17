package com.dawn.weather.study.kotlin.chapter03

/**
 *  @author: LXL
 *  @description: 泛型类 泛型方法
 *  @date: 2021/11/16 13:36
 */
class Generic<T> {

    fun method(param: T): T {
        return param
    }

}

class Generic2 {
    fun <T> method(param: T): T {
        return param
    }

    fun <T : Number> method2(param: T): T {
        return param
    }
}

fun main() {
    val generic = Generic<String>()
    println(generic.method("123123"))

    val generic2 = Generic2()
    println(generic2.method("666"))

    println(generic2.method2(888))
}

