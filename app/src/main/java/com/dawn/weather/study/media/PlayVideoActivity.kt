package com.dawn.weather.study.media

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import com.dawn.weather.R

/**
 * 播放视频
 */
class PlayVideoActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_video)
        title = "视频"
        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        videoView = findViewById(R.id.videoView)
        videoView.setVideoURI(uri)

        findViewById<Button>(R.id.btn_play_videos).setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start() // 开始播放
            }
        }
        findViewById<Button>(R.id.btn_pause_video).setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause() // 暂停播放
            }
        }
        findViewById<Button>(R.id.btn_replay_video).setOnClickListener {
            if (videoView.isPlaying) {
                videoView.resume() // 重新播放
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        videoView.suspend()
    }
}