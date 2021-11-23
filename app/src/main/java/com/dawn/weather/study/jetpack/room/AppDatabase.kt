package com.dawn.weather.study.jetpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dawn.weather.study.data.User

/**
 *  @author: LXL
 *  @description: 这部分内容的写法是非常固定的，
 *  只需要定义好3个部分的内容：数据库的版本号、包含哪些实体类，以及提供Dao层的访问实例
 *  @date: 2021/11/22 14:08
 */
@Database(version = 1, entities = [User::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            //注意第一个参数一定要使用applicationContext，而不能使用普通的context，否则容易出现内存泄漏的情况，
            // 第二个参数是AppDatabase的Class类型，
            // 第三个参数是数据库名，这些都比较简单。
            // 最后调用build()方法完成构建，并将创建出来的实例赋值给instance变量，然后返回当前实例即可。
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "app_database"
            )
                //加入一个allowMainThreadQueries()方法，这样Room就允许在主线程中进行数据库操作了，这个方法建议只在测试环境下使用
                //.allowMainThreadQueries()

                .build().apply {
                    instance = this
                }
        }
    }
}