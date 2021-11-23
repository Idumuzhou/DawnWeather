package com.dawn.weather.study.jetpack.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.dawn.weather.R
import com.orhanobut.logger.Logger
import java.util.concurrent.TimeUnit

/**
 * WorkManager  国产手机上可能会非常不稳定
 * WorkManager的基本用法其实非常简单，主要分为以下3步：
(1) 定义一个后台任务，并实现具体的任务逻辑；
(2) 配置该后台任务的运行条件和约束信息，并构建后台任务请求；
(3) 将该后台任务请求传入WorkManager的enqueue()方法中，系统会在合适的时间运行。
 */
class WorkManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        findViewById<Button>(R.id.doWorkBtn).setOnClickListener {
            //OneTimeWorkRequest.Builder是WorkRequest.Builder的子类，用于构建单次运行的后台任务请求。
            // WorkRequest.Builder还有另外一个子类PeriodicWorkRequest.Builder，
            // 可用于构建周期性运行的后台任务请求，但是为了降低设备性能消耗，
            // PeriodicWorkRequest.Builder构造函数中传入的运行周期间隔不能短于15分钟
            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
                //SimpleWorker这个后台任务在5秒后运行
                .setInitialDelay(5, TimeUnit.SECONDS)
                //可以通过标签来取消后台任务请求：
                .addTag("simple")
                //setBackoffCriteria()方法来重新执行任务的
                //第二个和第三个参数用于指定在多久之后重新执行任务，时间最短不能少于10秒钟；
                // 第一个参数则用于指定如果任务再次执行失败，下次重试的时间应该以什么样的形式延迟。
                // 这其实很好理解，假如任务一直执行失败，不断地重新执行似乎并没有什么意义，只会徒增设备的性能消耗。
                // 而随着失败次数的增多，下次重试的时间也应该进行适当的延迟，这才是更加合理的机制。
                // 第一个参数的可选值有两种，分别是LINEAR和EXPONENTIAL，前者代表下次重试时间以线性的方式延迟，
                // 后者代表下次重试时间以指数的方式延迟。
                .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
                .build()
            WorkManager.getInstance(this).enqueue(request)

            //Tag取消
            WorkManager.getInstance(this).cancelAllWorkByTag("simple")
            //通过id来取消后台任务请求
            WorkManager.getInstance(this).cancelWorkById(request.id)

            //对后台任务的运行结果进行监听
            WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(request.id)
                .observe(this, Observer { workInfo ->
                    if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                        Logger.e("do work succeeded")
                    } else if (workInfo.state == WorkInfo.State.FAILED) {
                        Logger.e("do work failed")
                    }
                })

            //链式任务来实现 先同步、再压缩、最后上传的功能
            //beginWith()方法用于开启一个链式任务，至于后面要接上什么样的后台任务，
            // 只需要使用then()方法来连接即可。另外WorkManager还要求，
            // 必须在前一个后台任务运行成功之后，下一个后台任务才会运行。
            // 也就是说，如果某个后台任务运行失败，或者被取消了，那么接下来的后台任务就都得不到运行了。
            /*WorkManager.getInstance(this)
                .beginWith(sync)
                .then(compress)
                .then(upload)
                .enqueue()*/
        }
    }
}

/**
 * 前面所介绍的WorkManager的所有功能，在国产手机上都有可能得不到正确的运行。
 * 这是因为绝大多数的国产手机厂商在进行Android系统定制的时候会增加一个一键关闭的功能，
 * 允许用户一键杀死所有非白名单的应用程序。
 * 而被杀死的应用程序既无法接收广播，也无法运行WorkManager的后台任务。
 * 这个功能虽然与Android原生系统的设计理念并不相符，但是我们也没有什么解决办法。
 * 或许就是因为有太多恶意应用总是想要无限占用后台，国产手机厂商才增加了这个功能吧。
 * 因此，这里给你的建议就是，WorkManager可以用，但是千万别依赖它去实现什么核心功能，因为它在国产手机上可能会非常不稳定。
 * */