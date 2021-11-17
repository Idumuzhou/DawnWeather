package com.dawn.weather.study.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dawn.weather.R
import com.dawn.weather.ext.logE

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class LeftFragment : Fragment() {

    /**
     * 常量
     */
    companion object{
        private const val TAG = "LeftFragment"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        logE(TAG,"onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logE(TAG,"onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logE(TAG,"onCreateView()")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_left, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logE(TAG,"onActivityCreated()")
    }

    override fun onStart() {
        super.onStart()
        logE(TAG,"onStart()")
    }

    override fun onResume() {
        super.onResume()
        logE(TAG,"onResume()")
    }

    override fun onPause() {
        super.onPause()
        logE(TAG,"onPause()")
    }

    override fun onStop() {
        super.onStop()
        logE(TAG,"onStop()")
    }



    override fun onDestroyView() {
        super.onDestroyView()
        logE(TAG,"onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        logE(TAG,"onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        logE(TAG,"onDetach()")
    }
}