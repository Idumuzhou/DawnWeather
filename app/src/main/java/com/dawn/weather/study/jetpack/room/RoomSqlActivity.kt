package com.dawn.weather.study.jetpack.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.dawn.weather.R
import com.dawn.weather.ext.logD
import com.dawn.weather.study.data.User
import com.orhanobut.logger.Logger
import kotlin.concurrent.thread

/**
 * Room 数据库
下Room的整体结构。它主要由Entity、Dao和Database这3部分组成，每个部分都有明确的职责，详细说明如下。
Entity:用于定义封装实际数据的实体类，每个实体类都会在数据库中有一张对应的表，并且表中的列是根据实体类中的字段自动生成的。
Dao:Dao是数据访问对象的意思，通常会在这里对数据库的各项操作进行封装，在实际编程的时候，
逻辑层就不需要和底层数据库打交道了，直接和Dao层进行交互即可。
Database:用于定义数据库中的关键信息，包括数据库的版本号、包含哪些实体类以及提供Dao层的访问实例。
 */
class RoomSqlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_sql)
        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom", "Brady", 40)
        val user2 = User("Tom", "Hanks", 63)
        findViewById<Button>(R.id.addDataBtn).setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }
        findViewById<Button>(R.id.updateDataBtn).setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }

        }
        findViewById<Button>(R.id.deleteDataBtn).setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }

        }
        findViewById<Button>(R.id.queryDataBtn).setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()) {
                    Logger.e(user.toString())
                }
            }

        }
    }
}