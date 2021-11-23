package com.dawn.weather.study.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dawn.weather.R
import com.dawn.weather.ext.toastShort
import com.dawn.weather.study.base.BaseActivity


class RecyclerViewActivity : BaseActivity() {
    private var fruitData = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        fruitData()
        val recyclerView = findViewById<RecyclerView>(R.id.rv_list)
        //val linearLayoutManager = LinearLayoutManager(this)
        //linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        //GridLayoutManager  网格布局

        //StaggeredGridLayoutManager  瀑布流布局
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        recyclerView.adapter = FruitAdapter(fruitData)
    }

    private fun fruitData() {
        repeat(2) {
            fruitData.add(Fruit(getRandomLengthString("Apple")))
            fruitData.add(Fruit(getRandomLengthString("Banana")))
            fruitData.add(Fruit(getRandomLengthString("Orange")))
            fruitData.add(Fruit(getRandomLengthString("Watermelon")))
            fruitData.add(Fruit(getRandomLengthString("Pear")))
            fruitData.add(Fruit(getRandomLengthString("Grape")))
            fruitData.add(Fruit(getRandomLengthString("Pineapple")))
            fruitData.add(Fruit(getRandomLengthString("Strawberry")))
            fruitData.add(Fruit(getRandomLengthString("Cherry")))
            fruitData.add(Fruit(getRandomLengthString("Mango")))

        }
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }

}

class FruitAdapter(private val fruitList: List<Fruit>) : RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitName: TextView = view.findViewById(R.id.tv_item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.fruitName.setOnClickListener {
            toastShort(parent.context,fruitList[viewHolder.layoutPosition].name)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitName.text = fruit.name
    }

    override fun getItemCount(): Int = fruitList.size


}

data class Fruit(val name: String)

