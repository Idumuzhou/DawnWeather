package com.dawn.weather.study.kt

/**
 *  @author: LXL
 *  @description: 密封类  sealed
 *  @date: 2021/11/11 13:52
 */

//interface Result
sealed class Result  //密封类 将interface 关键字改成 sealed class
class Success(val msg: String) : Result()
class Failure(val error: Exception) : Result()

fun getResultMsg(result: Result) = when (result) {
    is Success -> result.msg
    is Failure -> "Error is ${result.error.message}"
    //非密封类需要else
    //else ->  throw IllegalArgumentException()
}