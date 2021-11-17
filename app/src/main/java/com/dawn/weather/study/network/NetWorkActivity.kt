package com.dawn.weather.study.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.dawn.weather.R
import com.dawn.weather.ext.logE
import com.dawn.weather.study.util.HttpCallbackListener
import com.dawn.weather.study.util.HttpUtil
import com.orhanobut.logger.Logger
import okhttp3.*
import org.xml.sax.Attributes
import org.xml.sax.InputSource
import org.xml.sax.helpers.DefaultHandler
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.StringReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import javax.xml.parsers.SAXParserFactory
import kotlin.Exception
import kotlin.concurrent.thread

/**
 * 网络相关 Http OkHttp
 * 数据解析 XML JSON
 */
class NetWorkActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "NetWorkActivity"
        private const val url = "https://www.baidu.com"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_work)
        title = "网络"

        requestBaidu()

        okHttpRequest()

        /**
         * 没有封装线程
         */
        thread {
            Logger.e("没有封装线程-->${HttpUtil.sendHttpRequest(url)}")
        }

        /**
         * 线程封装 接口回调
         */
        HttpUtil.sendHttpRequest2(url,object : HttpCallbackListener{
            override fun onFinish(response: String) {
                Logger.e("线程封装 接口回调-->$response")
            }

            override fun onError(e: Exception) {

            }
        })

        /**
         * OkHttp 封装
         */
        HttpUtil.sendOkHttpRequest(url,object : Callback{
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                Logger.e("OkHttp 封装-->${response.body?.string()}")
            }

        })
    }

    /**
     * OkHttp 请求
     */
    private fun okHttpRequest() {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    Logger.e(responseData)
                    runOnUiThread {
                        findViewById<TextView>(R.id.tv_network_content).text = responseData
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * HttpURLConnection 请求百度
     */
    private fun requestBaidu() {
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL(url)
                connection = url.openConnection() as HttpsURLConnection
                connection = connection.apply {
                    requestMethod = "GET"  //GET && POST
                    connectTimeout = 8000  //链接超时
                    readTimeout = 8000     //读取超时
                }

                val inputStream = connection.inputStream  //获取到服务器返回的输入流

                //POST 请求写法
                //val dataOutputStream = DataOutputStream(connection.outputStream)
                //dataOutputStream.writeBytes("username=admin&password=123456")

                // 下面对获取到的输入流进行读取
                val reader = BufferedReader(InputStreamReader(inputStream))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                logE(TAG, response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()  //关闭HTTP连接
            }

        }
    }

    /**
     * Pull解析方式
     */
    private fun parseXMLWithPull(xmlData: String) {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val xmlPullParser = factory.newPullParser()
            xmlPullParser.setInput(StringReader(xmlData))
            var eventType = xmlPullParser.eventType
            var id = ""
            var name = ""
            var version = ""
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val nodeName = xmlPullParser.name
                when (eventType) {
                    // 开始解析某个节点
                    XmlPullParser.START_TAG -> {
                        when (nodeName) {
                            "id" -> id = xmlPullParser.nextText()
                            "name" -> name = xmlPullParser.nextText()
                            "version" -> version = xmlPullParser.nextText()
                        }
                    }
                    // 完成解析某个节点
                    XmlPullParser.END_TAG -> {
                        if ("app" == nodeName) {
                            Log.d("MainActivity", "id is $id")
                            Log.d("MainActivity", "name is $name")
                            Log.d("MainActivity", "version is $version")
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 　SAX解析方式
     */
    private fun parseXMLWithSAX(xmlData: String) {
        try {
            val factory = SAXParserFactory.newInstance()
            val xmlReader = factory.newSAXParser().xmlReader
            val handler = ContentHandler()
            // 将ContentHandler的实例设置到XMLReader中
            xmlReader.contentHandler = handler
            // 开始执行解析
            xmlReader.parse(InputSource(StringReader(xmlData)))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}

class ContentHandler : DefaultHandler() {

    private var nodeName = ""

    private lateinit var id: StringBuilder

    private lateinit var name: StringBuilder

    private lateinit var version: StringBuilder

    override fun startDocument() {
        id = StringBuilder()
        name = StringBuilder()
        version = StringBuilder()
    }

    override fun startElement(uri: String, localName: String, qName: String, attributes: Attributes) {
        // 记录当前节点名
        nodeName = localName
        Log.d("ContentHandler", "uri is $uri")
        Log.d("ContentHandler", "localName is $localName")
        Log.d("ContentHandler", "qName is $qName")
        Log.d("ContentHandler", "attributes is $attributes")
    }

    override fun endElement(uri: String, localName: String, qName: String) {
        if ("app" == localName) {
            Log.d("ContentHandler", "id is ${id.toString().trim()}")
            Log.d("ContentHandler", "name is ${name.toString().trim()}")
            Log.d("ContentHandler", "version is ${version.toString().trim()}")
            // 最后要将StringBuilder清空
            id.setLength(0)
            name.setLength(0)
            version.setLength(0)
        }
    }

    override fun endDocument() {
    }

}