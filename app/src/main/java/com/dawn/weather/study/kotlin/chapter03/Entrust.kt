package com.dawn.weather.study.kotlin.chapter03

import kotlin.reflect.KProperty

/**
 *  @author: LXL
 *  @description: 类委托和委托属性  by
 *  @date: 2021/11/16 13:46
 *  委托是一种设计模式，它的基本理念是：操作对象自己不会去处理某段逻辑，
 *  而是会把工作委托给另外一个辅助对象去处理。
 */

fun main() {
    val hashSet = hashSetOf("1", "2", "3")
    val message = Entrust(hashSet)
    println(message)
    println(message.helloWorld())
    println(message.isEmpty())

    val entrust2 = Entrust2()

    println(entrust2.p)
}

class Entrust<T>(private val helperSet: HashSet<T>) : Set<T> by helperSet {
    fun helloWorld() = println("Hello World")
    override fun isEmpty() = false
}

class Entrust2{
    var p by Delegate()
}

class Delegate {

    var propValue: Any? = null

    operator fun getValue(myClass: Entrust2, prop: KProperty<*>): Any? {
        return propValue
    }

    operator fun setValue(myClass: Entrust2, prop: KProperty<*>, value: Any?) {
        propValue = value
    }

}