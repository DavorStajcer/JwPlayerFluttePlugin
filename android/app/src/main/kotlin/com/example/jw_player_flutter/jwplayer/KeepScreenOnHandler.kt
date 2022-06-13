package com.example.jw_player_flutter.jwplayer

import android.view.Window
import android.view.WindowManager
import com.jwplayer.pub.api.events.*
import com.jwplayer.pub.api.events.listeners.AdvertisingEvents
import com.jwplayer.pub.api.events.listeners.VideoPlayerEvents
import com.jwplayer.pub.view.JWPlayerView

/**
 *
 * Keeps the screen on while the video is playing.
 *
 */

class KeepScreenOnHandler(jwPlayerView: JWPlayerView, window: Window) :
    VideoPlayerEvents.OnPlayListener, VideoPlayerEvents.OnPauseListener,
    VideoPlayerEvents.OnCompleteListener,
    VideoPlayerEvents.OnErrorListener, AdvertisingEvents.OnAdPlayListener,
    AdvertisingEvents.OnAdPauseListener,
    AdvertisingEvents.OnAdCompleteListener, AdvertisingEvents.OnAdSkippedListener,
    AdvertisingEvents.OnAdErrorListener {

    private val mWindow: Window

    private fun updateWakeLock(enable: Boolean) {
        if (enable) {
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        } else {
            mWindow.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    override fun onError(errorEvent: ErrorEvent?) {
        updateWakeLock(false)
    }

    override fun onAdPlay(adPlayEvent: AdPlayEvent?) {
        updateWakeLock(true)
    }

    override fun onAdPause(adPauseEvent: AdPauseEvent?) {
        updateWakeLock(false)
    }

    override fun onAdComplete(adCompleteEvent: AdCompleteEvent?) {
        updateWakeLock(false)
    }

    override fun onAdSkipped(adSkippedEvent: AdSkippedEvent?) {
        updateWakeLock(false)
    }

    override fun onAdError(adErrorEvent: AdErrorEvent?) {
        updateWakeLock(false)
    }

    override fun onComplete(completeEvent: CompleteEvent?) {
        updateWakeLock(false)
    }

    override fun onPause(pauseEvent: PauseEvent?) {
        updateWakeLock(false)
    }

    override fun onPlay(playEvent: PlayEvent?) {
        updateWakeLock(true)
    }

    /* player destroyed */
    fun destroy() {
        updateWakeLock(false)
    }

    init {
        jwPlayerView.player.addListener(EventType.PLAY,this)
        jwPlayerView.player.addListener(EventType.PAUSE,this)
        jwPlayerView.player.addListener(EventType.COMPLETE,this)
        jwPlayerView.player.addListener(EventType.ERROR,this)
        jwPlayerView.player.addListener(EventType.AD_PLAY,this)
        jwPlayerView.player.addListener(EventType.AD_PAUSE,this)
        jwPlayerView.player.addListener(EventType.AD_COMPLETE,this)
        jwPlayerView.player.addListener(EventType.AD_ERROR,this)
        jwPlayerView.player.addListener(EventType.PLAY,this)
        jwPlayerView.player.addListener(EventType.PLAY,this)

        mWindow = window
    }
}