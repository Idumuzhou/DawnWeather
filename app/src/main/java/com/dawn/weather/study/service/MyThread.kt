package com.dawn.weather.study.service

import java.lang.Thread.sleep
import kotlin.concurrent.thread

/**
 *  @author: LXL
 *  @description: 线程
 *  @date: 2021/11/17 11:13
 */

fun main() {
    MyThread().start()

    Thread(MyRunnable()).start()

    Thread {
        for (i in 21..30) {
            sleep(100)
            println(i)
        }
    }.start()

    thread {
        for (i in 21..30) {
            sleep(100)
            println(i)
        }
    }
}

/**
 * 1.类继承自Thread
 */
class MyThread : Thread() {
    override fun run() {
        super.run()
        //编写具体逻辑
        for (i in 0..10) {
            sleep(600)
            println(i)
        }
    }
}

/**
 * 使用继承的方式耦合性有点高，
 * 我们会更多地选择使用实现Runnable接口的方式来定义一个线程
 */
class MyRunnable : Runnable {
    override fun run() {
        for (i in 11..20) {
            Thread.sleep(300)
            println(i)
        }
    }

}