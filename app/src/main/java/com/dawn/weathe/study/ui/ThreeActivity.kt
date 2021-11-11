package com.dawn.weathe.study.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.dawn.weathe.R
import com.dawn.weathe.ext.logD

class ThreeActivity : AppCompatActivity() {
    private val TAG = "ThreeActivity"
     private var data =  ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)
        logD(TAG,"当前Activity的 taskId:$taskId")
        title = "Three"
        data = intent.getStringExtra("Data").toString()

        findViewById<TextView>(R.id.tv_three).setOnClickListener {
            val intent = Intent()
            intent.putExtra("DataResult",data)
            setResult(RESULT_OK,intent)
            finish()
        }

        findViewById<Button>(R.id.btn_uri).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com")
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("DataResult","返回数据")
        setResult(RESULT_OK,intent)
        finish()
    }
}