package com.example.jw_player_flutter.jwplayer

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.view.KeyEvent
import android.widget.FrameLayout
import com.jwplayer.pub.api.configuration.PlayerConfig
import com.jwplayer.pub.api.configuration.ads.ima.ImaAdvertisingConfig
import com.jwplayer.pub.api.events.FullscreenEvent
import com.jwplayer.pub.api.events.listeners.VideoPlayerEvents
import com.jwplayer.pub.api.media.ads.AdBreak
import com.jwplayer.pub.api.media.playlists.PlaylistItem
import com.jwplayer.pub.view.JWPlayerView
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.JSONMethodCodec
import org.json.JSONObject

class PlayerLayout(
    context: Context,
    /**
     * App main activity
     */
    private val activity: Activity,
    private val messenger: BinaryMessenger,
    arguments: Any?,
    viewId: Int
) :
    FrameLayout(context), VideoPlayerEvents.OnFullscreenListener {
    /**
     * Reference to the [JWPlayerView]
     */
    private var mPlayerView: JWPlayerView? = null

    /**
     * Reference to the [PlayerConfig]
     */
    private lateinit var playerConfig: PlayerConfig
    private var keepScreenOnHandler: KeepScreenOnHandler? = null


    init {
        Log.d(LogTags.DEBUG,"Init player layout")

        try {
            val args = arguments as JSONObject?
            Log.d(LogTags.DEBUG,"Parrsking json data..")
            val playerMethodChannelData =
                PlayerMethodChannelData.createFromMethodChannelObject(args)
            Log.d(LogTags.DEBUG,"Parrsed data -> file ${playerMethodChannelData.file}, autoplay ${playerMethodChannelData.autoPlay},adconfigs ${playerMethodChannelData.adConfigs}, seek ${playerMethodChannelData.seek}")

            Log.d(LogTags.DEBUG,"Setting player config")

            setPlayerConfig(playerMethodChannelData)
            Log.d(LogTags.DEBUG,"Initializing player")
            initPlayer(playerMethodChannelData.seek, viewId)
        } catch (e: Exception) {
            /* ignore */
        }
    }

    private fun setPlayerConfig(playerMethodChannelData: PlayerMethodChannelData) {
        if (playerMethodChannelData.file == null) {
            throw NullPointerException()
        }
        val imaAdvertising = getImaAds(playerMethodChannelData.adConfigs)
        val playlist = getPlaylist(playerMethodChannelData.file)
        playerConfig = PlayerConfig.Builder()
            .playlist(playlist)
            .advertisingConfig(imaAdvertising)
            .autostart(playerMethodChannelData.autoPlay)
            .build()
    }

    private fun getImaAds(videoAdConfigs: List<VideoAdConfig>): ImaAdvertisingConfig {
        val schedule: MutableList<AdBreak> = ArrayList()
        for (videoAdConfig in videoAdConfigs) {
            val adBreak = AdBreak.Builder()
                .tag(videoAdConfig.tagUrl)
                .offset(videoAdConfig.offset)
                .build()
            schedule.add(adBreak)
        }
        return ImaAdvertisingConfig.Builder()
            .schedule(schedule)
            .build()
    }

    private fun getPlaylist(file: String?): List<PlaylistItem> {
        val playlistItem = PlaylistItem.Builder()
            .file(file)
            .build()
        val playlist: MutableList<PlaylistItem> = ArrayList()
        playlist.add(playlistItem)
        return playlist
    }

    private fun initPlayer(seek: Double, viewId: Int) {
        Log.d(LogTags.DEBUG,"Crrerating jwplayer for id $viewId")
        mPlayerView = JWPlayerView(activity)
       // mPlayerView!!.player.setup(playerConfig)
        //mPlayerView!!.player.addListener(EventType.FULLSCREEN, this)
        Log.d(LogTags.DEBUG,"adding on screen handler")

        keepScreenOnHandler = KeepScreenOnHandler(mPlayerView!!, activity.window)
        val mEventHandler = JWEventHandler(mPlayerView!!)
        val eventChannel = PlayerChannel
            .getEventChannel(messenger, viewId, JSONMethodCodec.INSTANCE)
        Log.d(LogTags.DEBUG,"Setting event handler for id $viewId")
        eventChannel.setStreamHandler(mEventHandler)
        mPlayerView!!.player.seek(seek)
        this.addView(mPlayerView)
    }

    public override fun onConfigurationChanged(newConfig: Configuration?) {

        /* set fullscreen when the device is rotated to landscape */

        if (mPlayerView != null) {
            mPlayerView!!.player.setFullscreen(
                newConfig?.orientation == Configuration.ORIENTATION_LANDSCAPE,
                true
            )
        }

        super.onConfigurationChanged(newConfig)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        /* exit fullscreen when the user pressed the Back button */
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mPlayerView!!.player.fullscreen) {
                mPlayerView!!.player.setFullscreen(false, true)
                return false
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onFullscreen(fullscreenEvent: FullscreenEvent) {
        val actionBar = activity.actionBar
        if (actionBar != null) {
            if (fullscreenEvent.fullscreen) {
                actionBar.hide()
            } else {
                actionBar.show()
            }
        }
    }

    fun retryFailedPlayback() {
        try {

            /* retry playback */
            mPlayerView!!.player.play()
        } catch (e: Exception) { /* ignore */
        }
    }

    fun onHostResume() {
        Log.d(LogTags.AD_TAG, "onHostResume")
        if (VideoAdManager.isPlayingAd) {
            mPlayerView!!.player.play()
        }
        try {

            /* let JW Player know that the app has returned from the background */

        } catch (e: Exception) { /* ignore */
        }
    }

    fun onHostPause() {
        try {

            /* let JW Player know that the app is going to the background */
        } catch (e: Exception) { /* ignore */
        }
    }

    fun onHostDestroy() {
        try {

            /* stop controlling screen wake lock */
            keepScreenOnHandler!!.destroy()

        } catch (e: Exception) { /* ignore */
        }
    }


}