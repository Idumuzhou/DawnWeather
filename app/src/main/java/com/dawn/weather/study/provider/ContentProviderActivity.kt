package com.dawn.weather.study.provider

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.contentValuesOf
import com.dawn.weather.R
import com.dawn.weather.ext.logD
import com.dawn.weather.ext.logE
import com.dawn.weather.ext.toastShort

/**
 * ContentProvider 内容提供者
 */
class ContentProviderActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ContentProviderActivity"
    }

    private var contactsList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)
        title = "ContentProvider 内容提供者"

        contentProvider()

        /**
         * 拨号请求权限
         */
        findViewById<Button>(R.id.btn_call_phone).setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
            } else {
                call()
            }
        }

        /**
         * 读取联系人
         */
        findViewById<Button>(R.id.btn_read_contacts).setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 2)
            } else {
                readContacts()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call()
                } else {
                    toastShort(this, "你拒绝了权限")
                }
            }
            2 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts()
                } else {
                    toastShort(this, "你拒绝了权限")
                }
            }
        }
    }

    /**
     * 拨号
     */
    private fun call() {
        try {
            //Intent.ACTION_DIAL  不需要要申请权限,直接跳转到拨号界面,并把号码带过去
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    /**
     * 读取联系人
     */
    private fun readContacts() {
        // 查询联系人数据
        contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
            ?.apply {
                while (moveToNext()) {
                    // 获取联系人姓名
                    val displayName = getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    // 获取联系人手机号
                    val number = getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    contactsList.add("$displayName\n$number")
                }
                logE(TAG, contactsList.toString())
                close()
            }
    }


    /**
     * 内容提供者增删改查
     */
    private fun contentProvider() {
        var bookId: String? = null

        findViewById<Button>(R.id.addData).setOnClickListener {
            // 添加数据
            val uri = Uri.parse("content://com.dawn.weather.provider/book")
            val values = contentValuesOf(
                "name" to "A Clash of Kings",
                "author" to "George Martin", "pages" to 1040, "price" to 22.85
            )
            val newUri = contentResolver.insert(uri, values)
            bookId = newUri?.pathSegments?.get(1)

        }
        findViewById<Button>(R.id.queryData).setOnClickListener {
            // 查询数据
            val uri = Uri.parse("content://com.dawn.weather.provider/book")
            contentResolver.query(uri, null, null, null, null)?.apply {
                while (moveToNext()) {
                    val name = getString(getColumnIndex("name"))
                    val author = getString(getColumnIndex("author"))
                    val pages = getInt(getColumnIndex("pages"))
                    val price = getDouble(getColumnIndex("price"))
                    logD(TAG, "book name is $name")
                    logD(TAG, "book author is $author")
                    logD(TAG, "book pages is $pages")
                    logD(TAG, "book price is $price")
                }
                close()
            }

        }
        findViewById<Button>(R.id.updateData).setOnClickListener {
            // 更新数据
            bookId?.let {
                val uri = Uri.parse("content://com.dawn.weather.provider/ book/$it")
                val values = contentValuesOf(
                    "name" to "A Storm of Swords",
                    "pages" to 1216, "price" to 24.05
                )
                contentResolver.update(uri, values, null, null)
            }

        }
        findViewById<Button>(R.id.deleteData).setOnClickListener {
            // 删除数据
            bookId?.let {
                val uri = Uri.parse("content://com.dawn.weather.provider/ book/$it")
                contentResolver.delete(uri, null, null)
            }

        }
    }
}