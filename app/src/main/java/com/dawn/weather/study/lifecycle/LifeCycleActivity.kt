package com.dawn.weather.study.lifecycle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.dawn.weather.R
import com.dawn.weather.ext.logD
import com.dawn.weather.ext.logE
import com.dawn.weather.study.base.BaseActivity

class LifeCycleActivity : BaseActivity() {
    private val TAG = "LifeCycleActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "生命周期"
        logE(TAG,"onCreate()")
        logD(TAG,this.toString())
        setContentView(R.layout.activity_normal)
        logD(TAG,"当前Activity的 taskId:$taskId")
        findViewById<Button>(R.id.btn_normal_second).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
            //startActivity(Intent(this,NormalActivity::class.java))
            //SecondActivity.actionStart(this,"Data1","Data2")
        }
        findViewById<Button>(R.id.btn_dialog_activity).setOnClickListener {
            startActivity(Intent(this,DialogActivity::class.java))
        }
        val tempData = savedInstanceState?.getString("data_key")
        tempData?.let { logE(TAG, it) }
    }

    override fun onStart() {
        super.onStart()
        logE(TAG,"onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        logE(TAG,"onRestart()")
    }

    override fun onResume() {
        super.onResume()
        logE(TAG,"onResume()")
    }

    override fun onPause() {
        super.onPause()
        logE(TAG,"onPause()")
    }

    override fun onStop() {
        super.onStop()
        logE(TAG,"onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        logE(TAG,"onDestroy()")
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logE(TAG,"onSaveInstanceState()")
        val tempData = "我是销毁是保存数据"
        outState.putString("data_key",tempData)

    }
}