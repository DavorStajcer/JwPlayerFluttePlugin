package com.example.jw_player_flutter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import io.flutter.plugin.platform.PlatformView

class TestPlatformView(
    val context: Context,
) : PlatformView {

    lateinit var textView: TextView

    override fun getView(): View {
        textView = TextView(this.context)
        textView.text = "Some random view"
        textView.setBackgroundColor(Color.WHITE)
        Log.d("someTag","initializing view")
        return textView
    }

    override fun dispose() {

    }
}