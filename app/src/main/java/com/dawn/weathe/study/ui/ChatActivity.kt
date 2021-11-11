package com.dawn.weathe.study.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dawn.weathe.R
import com.dawn.weathe.study.base.BaseActivity

/**
 * 聊天界面
 */
class ChatActivity : BaseActivity() {
    private val msgList = ArrayList<Msg>()

    private lateinit var adapter: MsgAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var inputText: EditText
    private lateinit var radioGroup: RadioGroup
    private var msgType = Msg.TYPE_SENT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initMsg()
        recyclerView = findViewById(R.id.recyclerView)
        inputText = findViewById(R.id.inputText)
        radioGroup = findViewById(R.id.radioGroup)


        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        //::adapter.isInitialized可用于判断adapter变量是否已经初始化
        if (!::adapter.isInitialized) {
            adapter = MsgAdapter(msgList)
        }

        recyclerView.adapter = adapter

        //点击发送
        findViewById<Button>(R.id.send).setOnClickListener {
            val content = inputText.text.toString()
            if (content.isNotEmpty()) {
                val msg = Msg(content, msgType)
                msgList.add(msg)
                adapter.notifyItemInserted(msgList.size - 1) // 当有新消息时，刷新RecyclerView中的显示
                recyclerView.scrollToPosition(msgList.size - 1)  // 将RecyclerView定位到最后一行
                inputText.setText("") // 清空输入框中的内容
            }
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.radioSend -> msgType = Msg.TYPE_SENT
                R.id.radioReceive -> msgType = Msg.TYPE_RECEIVED
            }
        }
    }

    private fun initMsg() {
        val msg1 = Msg("Hello guy.", Msg.TYPE_RECEIVED)
        msgList.add(msg1)
        val msg2 = Msg("Hello. Who is that?", Msg.TYPE_SENT)
        msgList.add(msg2)
        val msg3 = Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED)
        msgList.add(msg3)
    }


    class Msg(val content: String, val type: Int) {
        companion object {
            const val TYPE_RECEIVED = 0
            const val TYPE_SENT = 1
        }
    }

    class MsgAdapter(private val msgList: List<Msg>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        inner class LeftViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val leftMsg: TextView = view.findViewById(R.id.leftMsg)
        }

        inner class RightViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val rightMsg: TextView = view.findViewById(R.id.rightMsg)
        }

        override fun getItemViewType(position: Int): Int {
            val msg = msgList[position]
            return msg.type
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = if (viewType ==
            Msg.TYPE_RECEIVED
        ) {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.msg_left_item,
                parent, false
            )
            LeftViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.msg_right_item,
                parent, false
            )
            RightViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val msg = msgList[position]
            when (holder) {
                is LeftViewHolder -> holder.leftMsg.text = msg.content
                is RightViewHolder -> holder.rightMsg.text = msg.content
                else -> throw IllegalArgumentException()
            }
        }

        override fun getItemCount() = msgList.size
    }
}