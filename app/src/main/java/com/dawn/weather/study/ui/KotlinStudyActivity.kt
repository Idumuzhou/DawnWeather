package com.dawn.weather.study.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.dawn.weather.R
import com.dawn.weather.ext.toastLong
import com.dawn.weather.ext.toastShort
import com.dawn.weather.study.broadcast.BroadcastReceiverActivity
import com.dawn.weather.study.fragment.FragmentActivity
import com.dawn.weather.study.fragment.news.FragmentNewsActivity
import com.dawn.weather.study.lifecycle.LifeCycleActivity
import com.dawn.weather.study.media.CameraActivity
import com.dawn.weather.study.media.NotificationActivity
import com.dawn.weather.study.media.PlayAudioActivity
import com.dawn.weather.study.media.PlayVideoActivity
import com.dawn.weather.study.network.NetWorkActivity
import com.dawn.weather.study.network.RetrofitActivity
import com.dawn.weather.study.network.WebViewActivity
import com.dawn.weather.study.provider.ContentProviderActivity
import com.dawn.weather.study.service.ServiceActivity
import com.dawn.weather.study.service.ThreadActivity
import com.dawn.weather.study.storage.SQLiteActivity
import com.dawn.weather.study.storage.StorageActivity

class KotlinStudyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_study)
        supportActionBar?.hide()
        //findViewById<TitleLayout>(R.id.title_layout).setTitle("生命周期")
        findViewById<Button>(R.id.btn_second).setOnClickListener {
            val intent = Intent("ACTION_START")
            intent.addCategory("MY_CATEGORY")
            startActivity(intent)
        }
        findViewById<Button>(R.id.btn_lifecycle).setOnClickListener {
            startActivity(Intent(this,LifeCycleActivity::class.java))
        }
        findViewById<Button>(R.id.btn_recyclerview).setOnClickListener {
            startActivity(Intent(this,RecyclerViewActivity::class.java))
        }
        findViewById<Button>(R.id.btn_chat).setOnClickListener {
            startActivity(Intent(this,ChatActivity::class.java))
        }
        findViewById<Button>(R.id.btn_fragment).setOnClickListener {
            startActivity(Intent(this,FragmentActivity::class.java))
        }
        findViewById<Button>(R.id.btn_news).setOnClickListener {
            startActivity(Intent(this,FragmentNewsActivity::class.java))
        }
        findViewById<Button>(R.id.btn_broadcast_receiver).setOnClickListener {
            startActivity(Intent(this, BroadcastReceiverActivity::class.java))
        }
        findViewById<Button>(R.id.btn_storage).setOnClickListener {
            startActivity(Intent(this, StorageActivity::class.java))
        }
        findViewById<Button>(R.id.btn_sqlLit).setOnClickListener {
            startActivity(Intent(this, SQLiteActivity::class.java))
        }
        findViewById<Button>(R.id.btn_content_provider).setOnClickListener {
            startActivity(Intent(this, ContentProviderActivity::class.java))
        }
        findViewById<Button>(R.id.btn_notification).setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }
        findViewById<Button>(R.id.btn_camera).setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
        findViewById<Button>(R.id.btn_play_audio).setOnClickListener {
            startActivity(Intent(this, PlayAudioActivity::class.java))
        }
        findViewById<Button>(R.id.btn_play_video).setOnClickListener {
            startActivity(Intent(this, PlayVideoActivity::class.java))
        }
        findViewById<Button>(R.id.btn_thread).setOnClickListener {
            startActivity(Intent(this, ThreadActivity::class.java))
        }
        findViewById<Button>(R.id.btn_service).setOnClickListener {
            startActivity(Intent(this, ServiceActivity::class.java))
        }
        findViewById<Button>(R.id.btn_web_view).setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }
        findViewById<Button>(R.id.btn_new_work).setOnClickListener {
            startActivity(Intent(this, NetWorkActivity::class.java))
        }
        findViewById<Button>(R.id.btn_retrofit).setOnClickListener {
            startActivity(Intent(this, RetrofitActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.kotlin_study,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_item ->{
                toastShort(this,item.title)
            }
            R.id.remove_item ->{
                toastLong(this,item.title)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}