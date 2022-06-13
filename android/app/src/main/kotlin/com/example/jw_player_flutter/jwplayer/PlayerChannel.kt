package com.example.jw_player_flutter.jwplayer

import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodCodec

object PlayerChannel {
    fun getEventChannel(
        messenger: BinaryMessenger?,
        viewId: Int,
        codec: MethodCodec?
    ): EventChannel {
        return EventChannel(messenger, "tv.mta.jwplayer/JWPlayerView_EventChannel_$viewId", codec)
    }

    fun getMethodChannel(messenger: BinaryMessenger?, viewId: Int): MethodChannel {
        return MethodChannel(messenger, "tv.mta.jwplayer/JWPlayerView_MethodChannel_$viewId")
    }

    fun getMethodChannelLog(messenger: BinaryMessenger?): MethodChannel {
        return MethodChannel(messenger, "tv.mta.jwplayer/JWPlayerView_MethodChannel_log")
    }

    fun logOnMethodChannel(binaryMessenger: BinaryMessenger, text: String) {
        getMethodChannelLog(binaryMessenger).invokeMethod("log",text)
    }

}