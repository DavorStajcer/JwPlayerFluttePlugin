package com.example.jw_player_flutter

import android.content.res.Configuration
import android.util.Log
import com.example.jw_player_flutter.jwplayer.LogTags
import com.example.jw_player_flutter.jwplayer.PlayerChannel
import com.example.jw_player_flutter.jwplayer.PlayerViewFactory
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

class MainActivity : FlutterActivity() {

    private lateinit var playerViewFactory: PlayerViewFactory

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        flutterEngine.platformViewsController.registry.registerViewFactory("<random-view>",TestFactory())



        playerViewFactory = PlayerViewFactory(this, flutterEngine.dartExecutor.binaryMessenger)
        flutterEngine.platformViewsController.registry.registerViewFactory(
            "<jw-player-view>",
            playerViewFactory
        )


    }


    override fun onResume() {
        super.onResume()
       playerViewFactory.onResume()
    }

    override fun onPause() {
        super.onPause()
        playerViewFactory.onPause()

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        playerViewFactory.onConfigurationChange(newConfig)
    }
}
