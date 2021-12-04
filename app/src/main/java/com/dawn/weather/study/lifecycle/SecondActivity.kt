package com.dawn.weather.study.lifecycle

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.dawn.weather.R
import com.dawn.weather.ext.logD
import com.dawn.weather.ext.logE
import com.dawn.weather.ext.toastShort

class SecondActivity : AppCompatActivity() {
    private val TAG = "SecondActivity"


    /**
     * 两个页面由不同的人定义 需要传值时 可以有当前接受页面的人定义接受类型接收值，
     * 跳转过来的页面只需调用这个方法即可
     */
    companion object {
        fun actionStart(context: Context, data1: String, data2: String) {
            /*val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("param1",data1)
            intent.putExtra("param2",data2)
            context.startActivity(intent)*/
            //apply 函数简化
            val intent = Intent(context, SecondActivity::class.java).apply {
                putExtra("param1", data1)
                putExtra("param2", data2)
            }
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        logE(TAG, "onCreate()")

        logD(TAG, "当前Activity的 taskId:$taskId")
        title = "Second"
        findViewById<TextView>(R.id.tv_three).setOnClickListener {
            //隐式跳转
            /*val intent = Intent("ACTION_START")
            intent.addCategory("THREE_CATEGORY")
            startActivity(intent)*/
            //显示跳转
            val intent = Intent(this, ThreeActivity::class.java)
            intent.putExtra("Data", "hello Three")
            /**
             * 序列化数据
             */
            val personS = PersonS()
            personS.name = "Dawn"
            personS.age = 18
            intent.putExtra("PersonS", personS)

            val personP = PersonP()
            personP.name = "Dawn_P"
            personP.age = 26
            intent.putExtra("PersonP", personP)

            val personJ = PersonJ(name = "Dawn_J", age = 28)
            intent.putExtra("PersonJ", personJ)

            startActivityForResult(intent, 1)
        }

        //到生命周期activity
        findViewById<Button>(R.id.btn_three).setOnClickListener {
            startActivity(Intent(this, LifeCycleActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) toastShort(this, data!!.getStringExtra("DataResult").toString())
        }
    }

    override fun onStart() {
        super.onStart()
        logE(TAG, "onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        logE(TAG, "onRestart()")
    }

    override fun onResume() {
        super.onResume()
        logE(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        logE(TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        logE(TAG, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        logE(TAG, "onDestroy()")
    }
}