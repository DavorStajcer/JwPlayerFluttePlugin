package com.example.jw_player_flutter.jwplayer

import android.util.Log
import com.jwplayer.pub.api.events.*
import com.jwplayer.pub.api.events.listeners.AdvertisingEvents
import com.jwplayer.pub.api.events.listeners.VideoPlayerEvents
import com.jwplayer.pub.view.JWPlayerView
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.EventChannel.EventSink
import org.json.JSONObject

class JWEventHandler(jwPlayerView: JWPlayerView) :
    VideoPlayerEvents.OnSetupErrorListener,
    VideoPlayerEvents.OnPlaylistListener, VideoPlayerEvents.OnPlaylistItemListener,
    VideoPlayerEvents.OnPlayListener, VideoPlayerEvents.OnPauseListener,
    VideoPlayerEvents.OnBufferListener, VideoPlayerEvents.OnIdleListener,
    VideoPlayerEvents.OnErrorListener, VideoPlayerEvents.OnSeekListener,
    VideoPlayerEvents.OnTimeListener, VideoPlayerEvents.OnFullscreenListener,
    VideoPlayerEvents.OnAudioTracksListener, VideoPlayerEvents.OnAudioTrackChangedListener,
    VideoPlayerEvents.OnCaptionsListListener, VideoPlayerEvents.OnMetaListener,
    VideoPlayerEvents.OnPlaylistCompleteListener, VideoPlayerEvents.OnCompleteListener,
    VideoPlayerEvents.OnLevelsChangedListener, VideoPlayerEvents.OnLevelsListener,
    VideoPlayerEvents.OnCaptionsChangedListener, VideoPlayerEvents.OnControlsListener,
    VideoPlayerEvents.OnControlBarVisibilityListener,
    VideoPlayerEvents.OnDisplayClickListener, VideoPlayerEvents.OnMuteListener,
    VideoPlayerEvents.OnSeekedListener, VideoPlayerEvents.OnVisualQualityListener,
    VideoPlayerEvents.OnFirstFrameListener, VideoPlayerEvents.OnReadyListener,
    AdvertisingEvents.OnAdClickListener, AdvertisingEvents.OnAdCompleteListener,
    AdvertisingEvents.OnAdSkippedListener, AdvertisingEvents.OnAdErrorListener,
    AdvertisingEvents.OnAdImpressionListener, AdvertisingEvents.OnAdTimeListener,
    AdvertisingEvents.OnAdPauseListener, AdvertisingEvents.OnAdPlayListener,
    AdvertisingEvents.OnAdScheduleListener, AdvertisingEvents.OnBeforePlayListener,
    AdvertisingEvents.OnBeforeCompleteListener, AdvertisingEvents.OnAdBreakStartListener,
    AdvertisingEvents.OnAdBreakEndListener, EventChannel.StreamHandler {
    private var eventSink: EventSink? = null
    override fun onListen(o: Any, eventSink: EventSink) {
        this.eventSink = eventSink
    }

    override fun onCancel(o: Any) {
        eventSink = null
    }

    override fun onAdClick(adClickEvent: AdClickEvent) {
        Log.d(LogTags.AD_TAG, "onAdClick")

        //this.jwPlayerView.play();
        try {
            val message = JSONObject()
            message.put("name", "onAdClick")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onAdComplete(adCompleteEvent: AdCompleteEvent) {
        Log.d(LogTags.AD_TAG, "onAdComplete")
        VideoAdManager.isPlayingAd = false
        try {
            val message = JSONObject()
            message.put("name", "onAdComplete")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onAdError(adErrorEvent: AdErrorEvent) {
        Log.d(LogTags.AD_TAG, "onAdError")
        try {
            val message = JSONObject()
            message.put("name", "onAdError " + adErrorEvent.message)
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onAdImpression(adImpressionEvent: AdImpressionEvent) {
        Log.d(LogTags.AD_TAG, "onAdImpression")
        try {
            val message = JSONObject()
            message.put("name", "onAdImpression")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onAdPause(adPauseEvent: AdPauseEvent) {
        Log.d(LogTags.AD_TAG, "onAdPause")
        try {
            val message = JSONObject()
            message.put("name", "onAdPause")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onAdPlay(adPlayEvent: AdPlayEvent) {
        Log.d(LogTags.AD_TAG, "onAdPlay")
        VideoAdManager.isPlayingAd = true
        try {
            val message = JSONObject()
            message.put("name", "onAdPlay")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onAdSchedule(adScheduleEvent: AdScheduleEvent) {
        Log.d(LogTags.AD_TAG, "onAdSchedule")
        try {
            val message = JSONObject()
            message.put("name", "onAdSchedule")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onAdSkipped(adSkippedEvent: AdSkippedEvent) {
        Log.d(LogTags.AD_TAG, "onAdSkipped")
        try {
            val message = JSONObject()
            message.put("name", "onAdSkipped")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onAdTime(adTimeEvent: AdTimeEvent) {
        //Log.d(LogTags.AD_TAG, "onAdTime");
        try {
            val message = JSONObject()
            message.put("duration", adTimeEvent.duration.toString())
            message.put("position", adTimeEvent.position.toString())
            message.put("name", "onAdTime")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onBeforeComplete(beforeCompleteEvent: BeforeCompleteEvent) {
        Log.d(LogTags.AD_TAG, "onBeforeComplete")
        try {
            val message = JSONObject()
            message.put("name", "onBeforeComplete")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onBeforePlay(beforePlayEvent: BeforePlayEvent) {
        Log.d(LogTags.AD_TAG, "onBeforePlay")
        try {
            val message = JSONObject()
            message.put("name", "onBeforePlay")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onAudioTrackChanged(audioTrackChangedEvent: AudioTrackChangedEvent) {
        Log.d(LogTags.AD_TAG, "onAudioTrackChanged")
        try {
            val message = JSONObject()
            message.put("name", "onAudioTrackChanged")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onAudioTracks(audioTracksEvent: AudioTracksEvent) {
        Log.d(LogTags.AD_TAG, "onAudioTracks")
        try {
            val message = JSONObject()
            message.put("name", "onAudioTracks")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onBuffer(bufferEvent: BufferEvent) {
        Log.d(LogTags.AD_TAG, "onBuffer")
        try {
            val message = JSONObject()
            message.put("name", "onBuffer")
            message.put("oldState", bufferEvent.oldState)
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onCaptionsChanged(captionsChangedEvent: CaptionsChangedEvent) {
        Log.d(LogTags.AD_TAG, "onCaptionsChanged")
        try {
            val message = JSONObject()
            message.put("name", "onCaptionsChanged")
            message.put("currentTrack", captionsChangedEvent.currentTrack)
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onCaptionsList(captionsListEvent: CaptionsListEvent) {
        Log.d(LogTags.AD_TAG, "onCaptionsList")
        try {
            val message = JSONObject()
            message.put("name", "onCaptionsList")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onComplete(completeEvent: CompleteEvent) {
        Log.d(LogTags.AD_TAG, "onComplete")
        try {
            val message = JSONObject()
            message.put("name", "onComplete")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onControlBarVisibilityChanged(controlBarVisibilityEvent: ControlBarVisibilityEvent) {
        Log.d(LogTags.AD_TAG, "onControlBarVisibilityChanged")
        try {
            val message = JSONObject()
            message.put("name", "onControlBarVisibilityChanged")
            message.put("controls", controlBarVisibilityEvent.isVisible)
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onControls(controlsEvent: ControlsEvent) {
        Log.d(LogTags.AD_TAG, "onControls")
        try {
            val message = JSONObject()
            message.put("name", "onControls")
            message.put("controls", controlsEvent.controls)
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onDisplayClick(displayClickEvent: DisplayClickEvent) {
        Log.d(LogTags.AD_TAG, "onDisplayClick")
        try {
            val message = JSONObject()
            message.put("name", "onDisplayClick")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onError(errorEvent: ErrorEvent) {
        Log.d(LogTags.AD_TAG, "onError")
        try {
            val message = JSONObject()
            message.put("name", "onError")
            message.put("message", errorEvent.message)
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onFirstFrame(firstFrameEvent: FirstFrameEvent) {
        Log.d(LogTags.AD_TAG, "onFirstFrame")
        try {
            val message = JSONObject()
            message.put("name", "onFirstFrame")
            message.put("loadTime", firstFrameEvent.loadTime)
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onFullscreen(fullscreenEvent: FullscreenEvent) {
        Log.d(LogTags.AD_TAG, "onFullscreen")
        try {
            val message = JSONObject()
            message.put("name", "onFullscreen")
            message.put("fullscreen", fullscreenEvent.fullscreen)
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onIdle(idleEvent: IdleEvent) {
        Log.d(LogTags.AD_TAG, "onIdle")
        try {
            val message = JSONObject()
            message.put("name", "onIdle")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onLevelsChanged(levelsChangedEvent: LevelsChangedEvent) {
        Log.d(LogTags.AD_TAG, "onLevelsChanged")
        try {
            val message = JSONObject()
            message.put("name", "onLevelsChanged")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onLevels(levelsEvent: LevelsEvent) {
        Log.d(LogTags.AD_TAG, "onLevels")
        try {
            val message = JSONObject()
            message.put("name", "onLevels")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onMeta(metaEvent: MetaEvent) {
        Log.d(LogTags.AD_TAG, "onMeta")
        try {
            val message = JSONObject()
            message.put("name", "onMeta")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onMute(muteEvent: MuteEvent) {
        Log.d(LogTags.AD_TAG, "onMute")
        try {
            val message = JSONObject()
            message.put("name", "onMute")
            message.put("mute", muteEvent.mute)
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onPause(pauseEvent: PauseEvent) {
        Log.d(LogTags.AD_TAG, "onPause")
        try {
            val message = JSONObject()
            message.put("name", "onPause")
            message.put("oldState", pauseEvent.oldState)
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onPlay(playEvent: PlayEvent) {
        Log.d(LogTags.AD_TAG, "onPlay")
        try {
            val message = JSONObject()
            message.put("name", "onPlay")
            message.put("oldState", playEvent.oldState)
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onPlaylistComplete(playlistCompleteEvent: PlaylistCompleteEvent) {
        Log.d(LogTags.AD_TAG, "onPlaylistComplete")
        try {
            val message = JSONObject()
            message.put("name", "onPlaylistComplete")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onPlaylistItem(playlistItemEvent: PlaylistItemEvent) {
        Log.d(LogTags.AD_TAG, "onPlaylistItem")
        try {
            val message = JSONObject()
            message.put("name", "onPlaylistItem")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onPlaylist(playlistEvent: PlaylistEvent) {
        Log.d(LogTags.AD_TAG, "onPlaylist")
        try {
            val message = JSONObject()
            message.put("name", "onPlaylist")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onReady(readyEvent: ReadyEvent) {
        Log.d(LogTags.AD_TAG, "onReady")
        try {
            val message = JSONObject()
            message.put("name", "onReady")
            message.put("setupTime", readyEvent.setupTime)
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onSeek(seekEvent: SeekEvent) {
        Log.d(LogTags.AD_TAG, "onSeek")
        try {
            val message = JSONObject()
            message.put("name", "onSeek")
            message.put("offset", seekEvent.offset)
            message.put("position", seekEvent.position)
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onSeeked(seekedEvent: SeekedEvent) {
        Log.d(LogTags.AD_TAG, "onSeeked")
        try {
            val message = JSONObject()
            message.put("name", "onSeeked")
            message.put("position", seekedEvent.position)
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onSetupError(setupErrorEvent: SetupErrorEvent) {
        Log.d(LogTags.AD_TAG, "onSetupError")
        try {
            val message = JSONObject()
            message.put("name", "onSetupError")
            message.put("message", setupErrorEvent.message)
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onTime(timeEvent: TimeEvent) {
        //Log.d(TAG, "onTime");
        try {
            val message = JSONObject()
            message.put("name", "onTime")
            message.put("duration", timeEvent.duration)
            message.put("position", timeEvent.position)
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onVisualQuality(visualQualityEvent: VisualQualityEvent) {
        Log.d(LogTags.AD_TAG, "onVisualQuality")
        try {
            val message = JSONObject()
            message.put("name", "onVisualQuality")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onAdBreakStart(adBreakStartEvent: AdBreakStartEvent) {
        Log.d(LogTags.AD_TAG, "onAdBreakStart")
        try {
            val message = JSONObject()
            message.put("name", "onAdBreakStart")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    override fun onAdBreakEnd(adBreakEndEvent: AdBreakEndEvent) {
        Log.d(LogTags.AD_TAG, "onAdBreakEnd")
        try {
            val message = JSONObject()
            message.put("name", "onAdBreakEnd")
            eventSink!!.success(message)
        } catch (e: Exception) { /* ignore */
        }
    }

    init {
        // Subscribe to all JW Player events
        jwPlayerView.player.addListener(EventType.READY, this)
        jwPlayerView.player.addListener(EventType.FIRST_FRAME, this)
        jwPlayerView.player.addListener(EventType.SETUP_ERROR, this)
        jwPlayerView.player.addListener(EventType.PLAYLIST, this)
        jwPlayerView.player.addListener(EventType.PLAYLIST_ITEM, this)
        jwPlayerView.player.addListener(EventType.PAUSE, this)
        jwPlayerView.player.addListener(EventType.BUFFER, this)
        jwPlayerView.player.addListener(EventType.IDLE, this)
        jwPlayerView.player.addListener(EventType.ERROR, this)
        jwPlayerView.player.addListener(EventType.SEEK, this)
        jwPlayerView.player.addListener(EventType.TIME, this)
        jwPlayerView.player.addListener(EventType.FULLSCREEN, this)
        jwPlayerView.player.addListener(EventType.LEVELS, this)
        jwPlayerView.player.addListener(EventType.LEVELS_CHANGED, this)
        jwPlayerView.player.addListener(EventType.CAPTIONS_LIST, this)
        jwPlayerView.player.addListener(EventType.CAPTIONS_CHANGED, this)
        jwPlayerView.player.addListener(EventType.CONTROLS, this)
        jwPlayerView.player.addListener(EventType.CONTROLBAR_VISIBILITY, this)
        jwPlayerView.player.addListener(EventType.DISPLAY_CLICK, this)
        jwPlayerView.player.addListener(EventType.MUTE, this)
        jwPlayerView.player.addListener(EventType.VISUAL_QUALITY, this)
        jwPlayerView.player.addListener(EventType.SEEKED, this)
        jwPlayerView.player.addListener(EventType.AD_CLICK, this)
        jwPlayerView.player.addListener(EventType.AD_COMPLETE, this)
        jwPlayerView.player.addListener(EventType.AD_SKIPPED, this)
        jwPlayerView.player.addListener(EventType.AD_ERROR, this)
        jwPlayerView.player.addListener(EventType.AD_IMPRESSION, this)
        jwPlayerView.player.addListener(EventType.AD_TIME, this)
        jwPlayerView.player.addListener(EventType.AD_PAUSE, this)
        jwPlayerView.player.addListener(EventType.AD_PLAY, this)
        jwPlayerView.player.addListener(EventType.COMPLETE, this)
        jwPlayerView.player.addListener(EventType.BEFORE_PLAY, this)
        jwPlayerView.player.addListener(EventType.BEFORE_COMPLETE, this)
        jwPlayerView.player.addListener(EventType.AD_SCHEDULE, this)
        jwPlayerView.player.addListener(EventType.AD_BREAK_START, this)
    }
}