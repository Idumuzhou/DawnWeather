package com.dawn.weathe.study.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.dawn.weathe.R
import com.dawn.weathe.ext.toastLong
import com.dawn.weathe.ext.toastShort
import com.dawn.weathe.study.lifecycle.LifeCycleActivity

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