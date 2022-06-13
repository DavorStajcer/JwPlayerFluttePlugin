package com.example.jw_player_flutter.jwplayer

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.platform.PlatformView
import kotlin.Any
import kotlin.Int

class PlayerView internal constructor(
   val context: Context,
    activity: Activity,
    id: Int,
    messenger: BinaryMessenger,
    args: Any?
) :
    PlatformView, MethodCallHandler {
    private var player: PlayerLayout? = null

    init {
        val channel: MethodChannel = PlayerChannel.getMethodChannel(messenger, id)
        channel.setMethodCallHandler(this)
        if (VideoAdManager.playerLayout != null) {
            val actualLayout: View = VideoAdManager.playerLayout!!
            val parentViewGroup = actualLayout.parent as ViewGroup
            parentViewGroup.removeView(actualLayout)
            player = VideoAdManager.playerLayout
        } else {
            player = PlayerLayout(context, activity, messenger, args, id)
            VideoAdManager.playerLayout = player
        }
    }

    override fun getView(): View {
        return player!!
    }

    override fun dispose() {
        player?.onHostDestroy()
        VideoAdManager.playerLayout = null
    }

    fun onPause() {
        player?.onHostPause()
    }

    override fun onFlutterViewAttached(flutterView: View) {
        if (player != null) {
            super.onFlutterViewAttached(flutterView)
        }
        Log.d(LogTags.AD_TAG, "onFlutterViewAttached, player $player")
    }

    override fun onFlutterViewDetached() {
        super.onFlutterViewDetached();
        Log.d(LogTags.AD_TAG, "onFlutterViewDetached")
    }

    fun onResume() {
        Log.d(LogTags.AD_TAG, "onResume")
        player?.onHostResume()
    }

    fun onConfigurationChange(newConfig: Configuration?) {
        player?.onConfigurationChanged(newConfig)
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        if ("dispose" == call.method) {
            dispose()
            result.success(true)
        } else {
            result.notImplemented()
        }
    }


}