package com.example.fortnitevibing

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); //For night mode theme
        setContentView(R.layout.activity_main)
        var isVibing = false

        val videoView = findViewById<VideoView>(R.id.videoView)
        val vibingButton = findViewById<Button>(R.id.vibingButton)

        val videoPath = "android.resource://" + packageName + "/" + R.raw.fortniteblue

        val uri = Uri.parse(videoPath)
        videoView.setVideoURI(uri)

        videoView.requestFocus()
        GlobalScope.launch(Dispatchers.IO) {
            async{ loadConfiguration(videoView) }
        }

        vibingButton.setOnClickListener() {
            if (isVibing) {
                vibingButton.setText("Start Fortnite Vibing")
                isVibing = false
                videoView.pause()
            }
            else {
                vibingButton.setText("Stop Vibing")
                isVibing = true
                videoView.start()

            }
        }

        videoView!!.setOnCompletionListener {
            if (isVibing)
                videoView.start()
        }

    }

    private suspend fun loadConfiguration(videoView: VideoView){
        videoView.start()
        delay(600L)
        videoView.pause()
    }


}