package com.dawn.weather.study.jetpack.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.orhanobut.logger.Logger

/**
 *  @author: LXL
 *  @description: Worker
 *  @date: 2021/11/22 14:57
 */
class SimpleWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    /**
     * doWork()方法不会运行在主线程当中，因此你可以放心地在这里执行耗时逻辑
     */
    override fun doWork(): Result {
        Logger.e("do work in SimpleWorker")
        return Result.success()
    }
}