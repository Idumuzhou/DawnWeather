package com.dawn.weather.study.storage

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.dawn.weather.R
import com.dawn.weather.ext.logD
import com.dawn.weather.ext.toastShort

/**
 * 数据库
 */
class SQLiteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)
        title = "SQLite"
        val databaseHelper = DatabaseHelper(this, "BookStore.db", 2)
        //创建数据库
        findViewById<Button>(R.id.btn_create_database).setOnClickListener {
            databaseHelper.writableDatabase
        }


        /**
         * 添加数据
         */
        findViewById<Button>(R.id.btn_add_data_database).setOnClickListener {
            val db = databaseHelper.writableDatabase
            val values1 = ContentValues().apply {
                // 开始组装第一条数据
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.96)
            }
            db.insert("Book", null, values1)  // 插入第一条数据
            val values2 = ContentValues().apply {
                // 开始组装第二条数据
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 510)
                put("price", 19.95)
            }
            db.insert("Book", null, values2) // 插入第二条数据
            toastShort(this, "插入成功")
        }

        /**
         * 更新数据
         */
        findViewById<Button>(R.id.btn_update_data_database).setOnClickListener {
            val db = databaseHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 10.99)
            //修改The Da Vinci Code 的价格
            db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
            toastShort(this, "修改成功")
        }

        /**
         * 删除数据
         */
        findViewById<Button>(R.id.btn_delete_data_database).setOnClickListener {
            val db = databaseHelper.writableDatabase
            //删除pages大于500的数据
            db.delete("Book", "pages > ?", arrayOf("500"))
            toastShort(this, "删除成功")
        }

        /**
         * 查询数据
         */
        findViewById<Button>(R.id.btn_query_data_database).setOnClickListener {
            val db = databaseHelper.writableDatabase
            // 查询Book表中所有的数据
            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    // 遍历Cursor对象，取出数据并打印
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    logD("SQLiteActivity", "book name is $name")
                    logD("SQLiteActivity", "book author is $author")
                    logD("SQLiteActivity", "book pages is $pages")
                    logD("SQLiteActivity", "book price is $price")
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

        /**
         * 使用事务
         */
        findViewById<Button>(R.id.btn_replace_data_database).setOnClickListener {
            val db = databaseHelper.writableDatabase
            db.beginTransaction() // 开启事务
            try {
                db.delete("Book", null, null)
                if (true) {
                    // 手动抛出一个异常，让事务失败
                    throw NullPointerException()
                }
                val values = ContentValues().apply {
                    put("name", "Game of Thrones")
                    put("author", "George Martin")
                    put("pages", 720)
                    put("price", 20.85)
                }
                db.insert("Book", null, values)
                db.setTransactionSuccessful() // 事务已经执行成功
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction() // 结束事务
            }
        }
    }
}