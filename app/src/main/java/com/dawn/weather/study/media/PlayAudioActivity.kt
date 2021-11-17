package com.dawn.weather.study.media

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.dawn.weather.R

/**
 * 音频播放
 */
class PlayAudioActivity : AppCompatActivity() {
    private val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_audio)
        initMediaPlayer()
        title = "音频"
        findViewById<Button>(R.id.btn_play).setOnClickListener {
            if (!mediaPlayer.isPlaying){
                mediaPlayer.start()  //开始播放
            }
        }
        findViewById<Button>(R.id.btn_pause).setOnClickListener {
            if (mediaPlayer.isPlaying){
                mediaPlayer.pause()  //暂停播放
            }
        }
        findViewById<Button>(R.id.btn_stop).setOnClickListener {
            if (mediaPlayer.isPlaying){
                mediaPlayer.reset()  //停止播放
                initMediaPlayer()
            }
        }
    }

    private fun initMediaPlayer(){
        val assets = assets
        val openFd = assets.openFd("Faded.mp3")
        mediaPlayer.setDataSource(openFd.fileDescriptor,openFd.startOffset,openFd.length)
        mediaPlayer.prepare()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}