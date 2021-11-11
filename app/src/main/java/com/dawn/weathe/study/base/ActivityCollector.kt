package com.dawn.weathe.study.base

import android.app.Activity

/**
 *  @author: LXL
 *  @description: Activity 管理 单例
 *  @date: 2021/11/10 16:35
 */
object ActivityCollector {
    private var activities = ArrayList<Activity>()

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    fun finishAll() {
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
                //在销毁所有Activity的代码后面再加上杀掉当前进程的代码，以保证程序完全退出
                android.os.Process.killProcess(android.os.Process.myPid())
            }
        }
        activities.clear()
    }

}