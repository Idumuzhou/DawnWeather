package com.dawn.weather.study.storage

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.dawn.weather.R
import com.dawn.weather.ext.toastShort
import java.io.*
import java.lang.StringBuilder

/**
 * 数据存储全方案，详解持久化技术
 */
class StorageActivity : AppCompatActivity() {
    private lateinit var etStorageInput: EditText

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)
        title = "数据存储&&持久化"

        etStorageInput = findViewById(R.id.et_storage_input)

        /**
         * 文件存储
         */
        readFile().let {
            findViewById<TextView>(R.id.tv_storage_read).text = it
            etStorageInput.setText(it)
            etStorageInput.setSelection(it.length)
            toastShort(this, "读取成功")
        }


        /**
         * SharedPreferences 存
         */
        ///data/data/com.dawn.weather/shared_prefs/
        findViewById<Button>(R.id.btn_shared_preferences).setOnClickListener {
            val edit = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
            edit.putString("name", etStorageInput.text.toString())
            edit.putInt("age", 18)
            edit.apply()
        }
        /**
         * SharedPreferences 取
         */
        findViewById<Button>(R.id.btn_shared_preferences_get).setOnClickListener {
            val edit = getSharedPreferences("data", Context.MODE_PRIVATE)
            val name = edit.getString("name", "输入框内容为空")
            val age = edit.getInt("age", 0)
            findViewById<TextView>(R.id.tv_shared_preferences_data).text = "姓名:$name,年龄:$age"

            //使用当前类名进行名字存储
            //val preferences = getPreferences(Context.MODE_PRIVATE)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        etStorageInput.text.toString().let {
            saveFile(it)
        }
    }

    /**
     * 写入数据 openFileOutput
     * /data/data/com.dawn.weather/files/目录
     */
    private fun saveFile(inputText: String) {
        try {
            //默认是MODE_PRIVATE，表示当指定相同文件名的时候，所写入的内容将会覆盖原文件中的内容，
            //而MODE_APPEND则表示如果该文件已存在，就往文件里面追加内容，不存在就创建新文件
            val openFileOutput = openFileOutput("data", Context.MODE_PRIVATE)
            var bufferedWriter = BufferedWriter(OutputStreamWriter(openFileOutput))
            //use函数，这是Kotlin提供的一个内置扩展函数。它会保证在Lambda表达式中的代码全部执行完之后自动将外层的流关闭，
            // 这样就不需要我们再编写一个finally语句，手动去关闭流了
            bufferedWriter.use {
                it.write(inputText)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 　从文件中读取数据  openFileInput
     */
    private fun readFile(): String {
        val content = StringBuilder()
        try {
            val openFileOutput = openFileInput("data")
            val bufferedReader = BufferedReader(InputStreamReader(openFileOutput))
            bufferedReader.use {
                bufferedReader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return content.toString()
    }
}


/**
 * 高阶函数简化下面这段代码
 * val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
 * editor.putString("name", "Tom")
 * editor.putInt("age", 28)
 * editor.putBoolean("married", false)
 * editor.apply()
 */
fun SharedPreferences.open(block: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.block()
    editor.apply()
}

/**
 * 使用
 * getSharedPreferences("data", Context.MODE_PRIVATE).open {
 * putString("name", "Tom")
 * putInt("age", 28)
 * putBoolean("married", false)
 * }
*/