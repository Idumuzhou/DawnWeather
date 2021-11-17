package com.dawn.weather.study.kt

/**
 *  @author: LXL
 *  @description: 运算符重载 operator
 *  @date: 2021/11/11 17:11
 *  两个对象相加
 *  class Obj {
 *  operator fun plus(obj: Obj): Obj {
 *      // 处理相加的逻辑
 *  }
 *  }
 */

class Money(val value: Int) {
    operator fun plus(money: Money): Money {
        val sum = value + money.value
        return Money(sum)
    }

    operator fun plus(newValue: Int): Money {
        val sum = value + newValue
        return Money(sum)
    }


}

fun main() {
    /*val money1 = Money(10)
    val money2 = Money(8)
    val money3 = money1 + money2
    println( money3.value)*/

    val money1 = Money(5)
    val money2 = Money(10)
    val money3 = money1 + money2
    val money4 = money3 + 20
    println(money4.value)
}