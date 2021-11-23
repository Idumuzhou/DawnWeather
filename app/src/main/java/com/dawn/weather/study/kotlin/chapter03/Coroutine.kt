package com.dawn.weather.study.kotlin.chapter03

import kotlinx.coroutines.*

/**
 *  @author: LXL
 *  @description: 协程
 *  @date: 2021/11/18 13:44
 */

fun main() {
    //Global.launch函数每次创建的都是一个顶层协程，这种协程当应用程序运行结束时也会跟着一起结束。刚才的日志之所以无法打印出来
    /**
     * 方式1:开启协程 属于顶层协程 不建议使用
     */
    /*GlobalScope.launch {
        println("开启协程")
        delay(1500)  //函数可以让当前协程延迟指定时间后再运行,只能在协程的作用域或其他挂起函数中调用。
        print("协程完成")
    }
    Thread.sleep(1000)*/

    /**
     * 方式2  会阻塞线程  不建议使用
     */
    runBlocking {
        println("开启协程")
        delay(1000)
        println("协程完成")

        /**
         * 多个协程
         */
        launch {
            println("开启协程1")
            delay(1000)
            println("协程完成1")
        }

        launch {
            println("开启协程2")
            delay(1000)
            println("协程完成2")
        }
    }

    /**
     * 多个协程并发运行的效果
     */
    val startTime = System.currentTimeMillis()
    runBlocking {
        repeat(100000) {
            launch {
                //println(".")
                printDot()
            }
        }
    }
    val endTime = System.currentTimeMillis()
    println("耗时:${endTime - startTime}")


    runBlocking {
        coroutineScope {
            launch {
                for (i in 1..10) {
                    println(i)
                    delay(1000)
                }
            }
        }
        //下面操作会被挂起 等上面子协程执行完,才会往下走
        println("coroutineScope finished")
    }
    println("runBlocking finished")


    foo()
    bar()


    /**=================================实际项目中比较常用的写法==================================*/
    val job = Job()
    val scope = CoroutineScope(job)
    scope.launch {
        for (i in 1..10) {
            println("Job $i")
        }
        //当调用await()方法时，如果代码块中的代码还没执行完，那么await()方法会将当前协程阻塞住，直到可以获得async函数的执行结果
        val result = async {
            6 + 6
        }.await()
        println(result)
    }
    job.cancel()

    //串行运行 因为加了.await()
    runBlocking {
        val start = System.currentTimeMillis()
        val result1 = async {
            delay(1000)
            5 + 5
        }.await()
        val result2 = async {
            delay(1000)
            4 + 6
        }.await()
        println("result is ${result1 + result2}.")
        val end = System.currentTimeMillis()
        println("cost ${end - start} ms.")
    }

    println("====================================================")

    //并行运行 在相加的地方调用.await()
    runBlocking {
        val start = System.currentTimeMillis()
        val deferred1 = async {
            delay(1000)
            5 + 5
        }
        val deferred2 = async {
            delay(1000)
            4 + 6
        }
        println("result is ${deferred1.await() + deferred2.await()}.")
        val end = System.currentTimeMillis()
        println("cost ${end - start} milliseconds.")
    }
    println("=====================withContext()函数===============================")
    //可以将它理解成async函数的一种简化版写法
    runBlocking {
        val result = withContext(Dispatchers.Default) {
            5 + 5
        }
        println(result)
    }

}

/**
 * suspend 挂起函数 可以使用协程作用域函数
 */
suspend fun printDot() {
    println(".")
    delay(1000)
}

/**
 * coroutineScope函数也是一个挂起函数，因此可以在任何其他挂起函数中调用。
 * 它的特点是会继承外部的协程的作用域并创建一个子协程，借助这个特性，我们就可以给任意挂起函数提供协程作用域了
 */
suspend fun printDot2() = coroutineScope {
    launch {
        println(".")
        delay(1000)
    }
}

/**
 * foo
 */
fun foo() {
    a()
    b()
    c()
}

fun a() {
    println("aaaaaaaaaaaaaaaaaaaa")
}

fun b() {
    println("bbbbbbbbbbbbbbbbbbbb")
}

fun c() {
    println("cccccccccccccccccccc")
}

/**
 * bar
 */
fun bar() {
    x()
    y()
    z()
}

fun x() {
    println("xxxxxxxxxxxxxxxxxxxx")
}

fun y() {
    println("yyyyyyyyyyyyyyyyyyyy")
}

fun z() {
    println("zzzzzzzzzzzzzzzzzzzz")
}
