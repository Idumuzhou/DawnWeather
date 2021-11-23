package com.dawn.weather.study.design

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.dawn.weather.R
import com.dawn.weather.ext.toastShort
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlin.concurrent.thread

/**
 * Toolbar DrawerLayout NavigationView
 */
class MaterialDesignActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private lateinit var fruitAdapter: FruitAdapter
    private val fruitData = ArrayList<FruitBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_design)
        toolbar = findViewById(R.id.toolbar)
        //toolbar.title = "MaterialDesign"
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
        }
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.nav_drawer_left)
        mRecyclerView = findViewById(R.id.recycle_view_material)
        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        navigationView.setCheckedItem(R.id.navCall)  //Call菜单项设置为默认选中
        navigationView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }

        /**
         * FloatingActionButton
         */
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            //toastShort(this,"FloatingActionButton")
            Snackbar.make(this, it, "Snackbar", Snackbar.LENGTH_SHORT).setAction("Undo") {
                toastShort(this, "Data restored")
            }.show()
        }

        initFruits()

        val linearLayoutManager = GridLayoutManager(this, 2)
        mRecyclerView.layoutManager = linearLayoutManager

        fruitAdapter = FruitAdapter(this, fruitData)
        mRecyclerView.adapter = fruitAdapter


        mSwipeRefreshLayout.apply {
            setColorSchemeResources(
                R.color.refresh_color_1,
                R.color.refresh_color_2,
                R.color.refresh_color_3,
                R.color.refresh_color_4
            )
            setOnRefreshListener {
                refreshFruits(fruitAdapter)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun refreshFruits(adapter: FruitAdapter) {
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                initFruits()
                adapter.notifyDataSetChanged()
                mSwipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun initFruits(){
        fruitData.clear()
        val fruits = mutableListOf(FruitBean(
            "https://hbimg.huabanimg.com/390038dcbdd1bedb26ff84e8f3b06b9f1936553267626-HtBd59_fw658/format/webp",
            "苹果"
        ),FruitBean(
            "https://hbimg.huabanimg.com/f50498c74af06ed8f99195bfe264c0e49d1664939cd39-0n11Zg_fw658/format/webp",
            "橘子"
        ),FruitBean(
            "https://hbimg.huabanimg.com/029a407d296582b56684f4696ebd9ab1ca8d90df127f7-ZA3MWa_fw658/format/webp",
            "香蕉"
        ),FruitBean(
            "https://hbimg.huabanimg.com/6c93751a18c71cbdb46d77d616520976b3dd7d20118821-qmiOB0_fw658/format/webp",
            "榴莲"
        ))

        //数据
        repeat(50) {
            val index = (0 until fruits.size).random()
            fruitData.add(fruits[index])
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup -> toastShort(this, "BackUp")
            R.id.delete -> toastShort(this, "Delete")
            R.id.settings -> toastShort(this, "Settings")
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }
}

data class FruitBean(val img: String, val name: String)

class FruitAdapter(val context: Context, private val fruitList: List<FruitBean>) :
    RecyclerView.Adapter<FruitAdapter.FruitViewHolder>() {
    inner class FruitViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImg: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName: TextView = view.findViewById(R.id.fruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_fruit, parent, false)

        val viewHolder = FruitViewHolder(inflate)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            val intent = Intent(context, FruitDetailActivity::class.java).apply {
                putExtra(FruitDetailActivity.FRUIT_NAME, fruit.name)
                putExtra(FruitDetailActivity.FRUIT_IMAGE, fruit.img)
            }
            context.startActivity(intent)

        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        holder.fruitName.text = fruitList[position].name
        Glide.with(context).load(fruitList[position].img).into(holder.fruitImg)
    }

    override fun getItemCount(): Int = fruitList.size
}