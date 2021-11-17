package com.dawn.weather.study.storage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dawn.weather.ext.toastShort

/**
 *  @author: LXL
 *  @description: 数据库辅助类
 *  @date: 2021/11/15 16:11
 */
class DatabaseHelper(val context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {

    private val createBook = "create table Book (" +
            " id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)"

    private val createCategory = "create table Category (" +
            "id integer primary key autoincrement," +
            "category_name text," +
            "category_code integer)"


    /**
     * 创建数据库
     */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createBook)
        db.execSQL(createCategory)
        toastShort(context,"成功创建数据库")
    }

    /**
     * 升级数据库
     * 中执行了两条DROP语句，如果发现数据库中已经存在Book表或Category表，就将这两张表删除，
     * 然后调用onCreate()方法重新创建。这里先将已经存在的表删除，是因为如果在创建表时发现这张表已经存在了，就会直接报错。
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        with(db) {
            execSQL("drop table if exists Book")
            execSQL("drop table if exists Category")
            onCreate(this)
        }
    }
}