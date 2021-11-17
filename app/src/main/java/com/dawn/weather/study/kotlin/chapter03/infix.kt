package com.dawn.weather.study.kotlin.chapter03

/**
 *  @author: LXL
 *  @description: infix 函数
 *  @date: 2021/11/17 10:59
 */

fun main() {
    if ("Hello Kotlin".startsWith("Hello")) {
        // 处理具体的逻辑
    }

    if ("Hello Kotlin" beginsWith "Hello") {
        // 处理具体的逻辑
    }

    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    if (list.contains("Banana")) {
        // 处理具体的逻辑
    }

    if (list has "Banana"){
        //处理具体的逻辑
    }

    val map = mapOf("Apple" with 1, "Banana" with 2, "Orange" with 3, "Pear" with 4,
        "Grape" with 5)
}

infix fun String.beginsWith(prefix: String) = startsWith(prefix)

infix fun <T> Collection<T>.has(element: T) = contains(element)

infix fun <A, B> A.to(that: B): Pair<A, B> = Pair(this, that)

infix fun <A, B> A.with(that: B): Pair<A, B> = Pair(this, that)