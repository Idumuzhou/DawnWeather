package com.dawn.weather.study.util

/**
 *  @author: LXL
 *  @description: description
 *  @date: 2021/11/10 17:17
 */
/*
object Util {
    fun doAction(){
        println("do Action")
    }
}*/

/**
 * 使用单例类的写法会将整个类中的所有方法全部变成类似于静态方法的调用方式，
 * 而如果我们只是希望让类中的某一个方法变成静态方法的调用方式该怎么办呢？
 * 这个时候就可以使用刚刚在最佳实践环节用到的companion object了，示例如下：
 */
class Util {
    fun doAction(){
        println("do Action")
    }
    companion object{
        //@JvmStatic注解，那么Kotlin编译器就会将这些方法编译成真正的静态方法
        @JvmStatic
        fun doAction2(){
            println("do Action2")
        }
    }
}