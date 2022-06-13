package com.example.jw_player_flutter

import android.content.Context
import io.flutter.plugin.common.JSONMessageCodec
import io.flutter.plugin.common.MessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

class TestFactory() : PlatformViewFactory(JSONMessageCodec.INSTANCE) {


    override fun create(context: Context?, viewId: Int, args: Any?): PlatformView {
        return TestPlatformView(context!!)
    }
}