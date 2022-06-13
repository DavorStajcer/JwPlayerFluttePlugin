package com.example.jw_player_flutter.jwplayer

object VideoAdManager {
    var isPlayingAd = false;
    var playerLayout: PlayerLayout? = null
}

/*

class VideoAdManager private constructor() {
    var isPlayingAd = false
    var playerLayout: PlayerLayout? = null

    companion object {
        private var videoAdManager: VideoAdManager? = null
        fun instance(): VideoAdManager? {
            if (videoAdManager == null) {
                videoAdManager = VideoAdManager()
            }
            return videoAdManager
        }
    }
}
 */