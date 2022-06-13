package com.example.jw_player_flutter.jwplayer

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.widget.TextView
import io.flutter.Log
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.JSONMessageCodec
import io.flutter.plugin.common.JSONMethodCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory
import java.lang.String
import kotlin.Any
import kotlin.Int


class PlayerViewFactory(
    private val activity: Activity,
    private val messenger: BinaryMessenger,
) :
    PlatformViewFactory(JSONMessageCodec.INSTANCE) {
    private var playerView: PlayerView? = null
    override fun create(context: Context?, id: Int, args: Any?): PlatformView? {
        Log.d(LogTags.DEBUG, "Creating player view with id $id")
        playerView = PlayerView(context!!, activity, id, messenger, args)
        return playerView
    }

    fun onResume() {
        Log.v(LogTags.DEBUG, "on resume")

        playerView?.onResume()

    }

    fun onPause() {
        Log.v(LogTags.DEBUG, "on pause")

        playerView?.onPause()

    }

    fun onConfigurationChange(newConfig: Configuration?) {
        playerView?.onConfigurationChange(newConfig)

    }
}