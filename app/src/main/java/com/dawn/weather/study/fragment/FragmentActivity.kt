package com.dawn.weather.study.fragment

import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.dawn.weather.R
import com.dawn.weather.study.base.BaseActivity

class FragmentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        findViewById<Button>(R.id.btn_add_fragment).setOnClickListener {
            replaceFragment(AnotherRightFragment())
        }
        replaceFragment(RightFragment())
    }

    /**
     * 动态添加Fragment主要分为5步。
     * (1) 创建待添加Fragment的实例。
     * (2) 获取FragmentManager，在Activity中可以直接调用getSupportFragmentManager()方法获取。
     * (3) 开启一个事务，通过调用beginTransaction()方法开启。
     * (4) 向容器内添加或替换Fragment，一般使用replace()方法实现，需要传入容器的id和待添加的Fragment实例。
     * (5) 提交事务，调用commit()方法来完成
     */
    private fun replaceFragment(fragment:Fragment){
        val fragmentManager = supportFragmentManager
        val beginTransaction = fragmentManager.beginTransaction()
        beginTransaction.replace(R.id.right_fragment,fragment)
        beginTransaction.addToBackStack(null)  //在Fragment中实现返回栈
        beginTransaction.commit()
    }
}