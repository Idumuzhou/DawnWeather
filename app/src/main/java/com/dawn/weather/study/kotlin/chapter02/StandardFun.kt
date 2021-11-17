package com.dawn.weather.study.kotlin.chapter02

/**
 *  @copyright: 2019
 *  @project KotlinStudy
 *  @author LuoXuLiang
 *  @description:  标准函数
 *  @date: 2021/11/10 16:53
 */
fun main() {
    //eatAll()
    eatAllWith()
    eatAllRun()
}

/**
 * let函数
 * ?可为空
 * ?.判空  等于 if（person != null）
 * ?: 如果左边表达式的结果不为空就返回左边表达式的结果，否则就返回右边表达式的结果
 */
fun doStudyStandardFun(person: Person?) {
    //person?.eat()

    person?.let {
        it.eat()
        println("let")
    }
}

/**
 * with函数接收两个参数：第一个参数可以是一个任意类型的对象，
 * 第二个参数是一个Lambda表达式。with函数会在Lambda表达式中提供第一个参数对象的上下文，
 * 并使用Lambda表达式中的最后一行代码作为返回值返回。示例代码如下：
 * val result = with(obj) {
 *  // 这里是obj的上下文
 *  "value" // with函数的返回值
 *  }
 */
val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
fun eatAll() {
    val builder = StringBuilder()
    builder.append("Start eating fruits.\n")
    for (fruit in list) {
        builder.append(fruit).append("\n")
    }
    builder.append("Ate all fruits.")
    val result = builder.toString()
    println(result)
}

//精简
fun eatAllWith() {
    val result = with(StringBuilder()){
        append("With函数 Start eating fruits.\n")
        for (fruit in list) {
            append(fruit).append("\n")
        }
        append("With函数 Ate all fruits.")
        toString()
    }
    println(result)
}


/**
 * run函数
 * val result = obj.run {
 *  // 这里是obj的上下文
 *  "value" // run函数的返回值
 *  }
 */
fun eatAllRun() {
    val result = StringBuilder().run{
        append("Run函数 Start eating fruits.\n")
        for (fruit in list) {
            append(fruit).append("\n")
        }
        append("Run函数 Ate all fruits.")
        toString()
    }
    println(result)
}

/**
 * apply函数
 * val result = obj.apply {
 *  // 这里是obj的上下文
 *  }
 *  // result == obj
 *  ，由于apply函数无法指定返回值，只能返回调用对象本身，
 *  因此这里的result实际上是一个StringBuilder对象
 */
fun eatAllApply() {
    val result = StringBuilder().apply{
        append("Run函数 Start eating fruits.\n")
        for (fruit in list) {
            append(fruit).append("\n")
        }
        append("Run函数 Ate all fruits.")
    }
    println(result.toString())
}

