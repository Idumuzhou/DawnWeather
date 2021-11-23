package com.dawn.weather.study.kotlin.chapter03

import kotlin.math.max

/**
 *  @author: LXL
 *  @description: 取最大值
 *  @date: 2021/11/22 9:38
 */

fun main() {
    //2个数对比大小
    val a = 10
    val b = 15
    val larger = max(a, b)

    //3个数对比大小
    val c = 5
    val largest = max(max(a, b), c)

    println(max(1, 2, 3, 4, 5, 6))
}

/**
 * vararg关键字，它允许方法接收任意多个同等类型的参数
 */
fun max(vararg nums: Int): Int {
    var maxNum = Int.MIN_VALUE
    for (num in nums) {
        maxNum = max(maxNum, num)
    }
    return maxNum
}

fun <T : Comparable<T>> max(vararg nums: T): T {
    if (nums.isEmpty()) throw RuntimeException("Params can not be empty.")
    var maxNum = nums[0]
    for (num in nums) {
        if (num > maxNum) {
            maxNum = num
        }
    }
    return maxNum
}