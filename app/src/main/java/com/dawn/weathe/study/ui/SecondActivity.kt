package com.dawn.weathe.study.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.dawn.weathe.R
import com.dawn.weathe.ext.logD
import com.dawn.weathe.ext.logE
import com.dawn.weathe.ext.toastShort
import com.dawn.weathe.study.lifecycle.LifeCycleActivity

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
                putExtra("param1",data1)
                putExtra("param2",data2)
            }
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
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


    override fun onDestroy() {
        super.onDestroy()
        logE(TAG, "onDestroy()")
    }
}