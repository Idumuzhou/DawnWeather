package com.dawn.weather.ui.place

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dawn.weather.R
import com.dawn.weather.ext.toastShort
import com.dawn.weather.ui.adapter.PlaceAdapter

/**
 *  @author: LXL
 *  @description: 地区天气
 *  @date: 2021/11/23 14:59
 */
class PlaceFragment : Fragment() {

    val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchPlaceEdit: EditText
    private lateinit var bgImageView: ImageView
    private lateinit var adapter: PlaceAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.fragment_place, container, false)
        recyclerView = inflate.findViewById(R.id.recyclerView)
        searchPlaceEdit = inflate.findViewById(R.id.searchPlaceEdit)
        bgImageView = inflate.findViewById(R.id.bgImageView)
        return inflate
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        adapter = PlaceAdapter(this, viewModel.placeList)
        recyclerView.adapter = adapter
        searchPlaceEdit.addTextChangedListener { editable ->
            val content = editable.toString()
            /*if (content.isNotEmpty()) {
                viewModel.searchPlaces(content)
            } else {
                recyclerView.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }*/
            if (content.isEmpty()) {
                recyclerView.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }
        searchPlaceEdit.setOnEditorActionListener { v: TextView?, actionId: Int, event: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.searchPlaces(searchPlaceEdit.text.toString())
                true
            }
            false
        }
        activity?.let {
            viewModel.placeLiveData.observe(it, Observer { result ->
                val places = result.getOrNull()
                if (places != null) {
                    recyclerView.visibility = View.VISIBLE
                    bgImageView.visibility = View.GONE
                    viewModel.placeList.clear()
                    viewModel.placeList.addAll(places)
                    adapter.notifyDataSetChanged()
                } else {
                    toastShort(it, "未能查询到任何地点")
                    result.exceptionOrNull()?.printStackTrace()
                }
            })
        }
    }
}