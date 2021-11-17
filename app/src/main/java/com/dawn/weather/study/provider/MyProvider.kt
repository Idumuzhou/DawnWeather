package com.dawn.weather.study.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

/**
 *  @author: LXL
 *  @description: MyProvider
 *  @date: 2021/11/16 12:56
 */
class MyProvider : ContentProvider() {
    private val table1Dir = 0
    private val table1Item = 1
    private val table2Dir = 2
    private val table2Item = 3

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        uriMatcher.addURI("com.dawn.weather.provider", "table1", table1Dir)
        uriMatcher.addURI("com.dawn.weather.provider ", "table1/#", table1Item)
        uriMatcher.addURI("com.dawn.weather.provider ", "table2", table2Dir)
        uriMatcher.addURI("com.dawn.weather.provider ", "table2/#", table2Item)
    }

    /**
     * 初始化ContentProvider的时候调用。通常会在这里完成对数据库的创建和升级等操作，
     * 返回true表示ContentProvider初始化成功，返回false则表示失败。
     */
    override fun onCreate(): Boolean {
        return true
    }

    /**
     * 从ContentProvider中查询数据。uri参数用于确定查询哪张表，projection参数用于确定查询哪些列，
     * selection和selectionArgs参数用于约束查询哪些行，sortOrder参数用于对结果进行排序，查询的结果存放在Cursor对象中返回。
     */
    override fun query(
        uri: Uri, projection: Array<out String>?, selection: String?,
        selectionArgs: Array<out String>?, sortOrder: String?
    ): Cursor? {
        when (uriMatcher.match(uri)) {
            table1Dir -> {
                // 查询table1表中的所有数据
            }
            table1Item -> {
                // 查询table1表中的单条数据
            }
            table2Dir -> {
                // 查询table2表中的所有数据
            }
            table2Item -> {
                // 查询table2表中的单条数据
            }
        }
        return null
    }

    /**
     * 根据传入的内容URI返回相应的MIME类型。
     */
    override fun getType(uri: Uri): String? = when (uriMatcher.match(uri)) {
        table1Dir -> "vnd.android.cursor.dir/vnd.com.dawn.weather.provider.table1"
        table1Item -> "vnd.android.cursor.item/vnd.com.dawn.weather.provider.table1"
        table2Dir -> "vnd.android.cursor.dir/vnd.com.dawn.weather.provider.table2"
        table2Item -> "vnd.android.cursor.item/vnd.com.dawn.weather.provider.table2"
        else -> null
    }

    /**
     * 向ContentProvider中添加一条数据。uri参数用于确定要添加到的表，待添加的数据保存在values参数中。
     * 添加完成后，返回一个用于表示这条新记录的URI。
     */
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return Uri.parse("")
    }

    /**
     * 从ContentProvider中删除数据。uri参数用于确定删除哪一张表中的数据，
     * selection和selectionArgs参数用于约束删除哪些行，被删除的行数将作为返回值返回。
     */
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return -1
    }

    /**
     * 更新ContentProvider中已有的数据。uri参数用于确定更新哪一张表中的数据，新数据保存在values参数中，
     * selection和selectionArgs参数用于约束更新哪些行，受影响的行数将作为返回值返回。
     */
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        return -1
    }
}