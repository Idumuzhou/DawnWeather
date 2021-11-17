package com.dawn.weather.study.kotlin.chapter02

/**
 *  @copyright: 2019
 *  @project KotlinStudy
 *  @author LuoXuLiang
 *  @description:  description
 *  @date: 2021/11/6 17:05
 */
 class Student(private val sno: String, private val grade:String) : Person(), Study {
    init {
        println("sno is $sno")
        println("grade is $grade")

    }

    override fun readBook() {
        println("${name}-->readBook")
    }

    override fun doHomework() {
        println("${name}-->doHomework")
    }
}