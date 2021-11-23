package com.dawn.weather.study.lifecycle

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.dawn.weather.R
import com.dawn.weather.ext.logD
import com.orhanobut.logger.Logger

class ThreeActivity : AppCompatActivity() {
    private val TAG = "ThreeActivity"
    private var data = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)
        logD(TAG, "当前Activity的 taskId:$taskId")
        title = "Three"
        data = intent.getStringExtra("Data").toString()


        val personS = intent.getSerializableExtra("PersonS") as PersonS
        Logger.e("Serializable--> ${personS.name}  ${personS.age}")

        val personP = intent.getParcelableExtra<PersonP>("PersonP")
        Logger.e("Parcelable--> ${personP?.name}  ${personP?.age}")

        val personJ = intent.getParcelableExtra<PersonJ>("PersonJ")
        Logger.e("Parcelable 注解简化--> ${personJ?.name}  ${personJ?.age}")

        findViewById<TextView>(R.id.tv_three).setOnClickListener {
            val intent = Intent()
            intent.putExtra("DataResult", data)
            setResult(RESULT_OK, intent)
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
        intent.putExtra("DataResult", "返回数据")
        setResult(RESULT_OK, intent)
        finish()
    }
}