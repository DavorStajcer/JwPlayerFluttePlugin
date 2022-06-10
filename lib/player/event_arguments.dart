abstract class PlayerEvent {}

class AdCompleteEvent implements PlayerEvent {
  static const String name = "onAdComplete";
}

class AdErrorEvent implements PlayerEvent {
  static const String name = "onAdError";
}

class AdImpressionEvent implements PlayerEvent {
  static const String name = "onAdImpression";
}

class AdPauseEvent implements PlayerEvent {
  static const String name = "onAdPause";
}

class AdPlayEvent implements PlayerEvent {
  static const String name = "onAdPlay";
}

class AdScheduleEvent implements PlayerEvent {
  static const String name = "onAdSchedule";
}

class AdSkipEvent implements PlayerEvent {
  static const String name = "onAdSkipped";
}

class AdTimeEvent implements PlayerEvent {
  final double duration;
  final double positon;

  AdTimeEvent(Map<String, dynamic> event)
      : duration = event["duration"] ?? 0.0,
        positon = event["position"] ?? 0.0;
  static const String name = "onAdTime";
}

class BeforeCompleteEvent implements PlayerEvent {
  static const String name = "onBeforeComplete";
}

class BeforePlayEvent implements PlayerEvent {
  static const String name = "onBeforePlay";
}

class AudioTracksEvent implements PlayerEvent {
  static const String name = "onAudioTracks";
}

class BufferEvent implements PlayerEvent {
  static const String name = "onBuffer";
}

class CaptionsChangedEvent implements PlayerEvent {
  final int currentTrack;

  CaptionsChangedEvent(Map<String, dynamic> event)
      : currentTrack = event["currentTrack"] ?? 0;

  static const String name = "onCaptionsChanged";
}

class CaptionsListEvent implements PlayerEvent {
  static const String name = "onCaptionsList";
}

class CompleteEvent implements PlayerEvent {
  static const String name = "onComplete";
}

class ControlBarVisibilityChangedEvent implements PlayerEvent {
  final bool isVisible;

  ControlBarVisibilityChangedEvent(Map<String, dynamic> event)
      : isVisible = event["controls"];
  static const String name = "onControlBarVisibilityChanged";
}

class ControlsEvent implements PlayerEvent {
  final bool controls;

  ControlsEvent(Map<String, dynamic> event) : controls = event["controls"];
  static const String name = "onControls";
}

class DisplayClickEvent implements PlayerEvent {
  static const String name = "onDisplayClick";
}

class ErrorEvent implements PlayerEvent {
  final String message;

  ErrorEvent(Map<String, dynamic> event) : message = event["message"];
  static const String name = "onError";
}

class FirstFrameEvent implements PlayerEvent {
  final double loadTime;

  FirstFrameEvent(Map<String, dynamic> event) : loadTime = event["loadTime"];
  static const String name = "onFirstFrame";
}

class FullscreenEvent implements PlayerEvent {
  final bool isFullscreen;

  FullscreenEvent(Map<String, dynamic> event)
      : isFullscreen = event["fullscreen"];
  static const String name = "onFullscreen";
}

class IdleEvent implements PlayerEvent {
  static const String name = "onIdle";
}

class LevelsChangedEvent implements PlayerEvent {
  static const String name = "onLevelsChanged";
}

class LevelsEvent implements PlayerEvent {
  static const String name = "onLevels";
}

class MetaEvent implements PlayerEvent {
  static const String name = "onMeta";
}

class MuteEvent implements PlayerEvent {
  final bool isMute;

  MuteEvent(Map<String, dynamic> event) : isMute = event["mute"];
  static const String name = "onMute";
}

class PauseEvent implements PlayerEvent {
  static const String name = "onPause";
}

class PlayEvent implements PlayerEvent {
  static const String name = "onPlay";
}

class PlaylistCompleteEvent implements PlayerEvent {
  static const String name = "onPlaylistComplete";
}

class PlaylistItemEvent implements PlayerEvent {
  static const String name = "onPlaylistItem";
}

class PlaylistEvent implements PlayerEvent {
  static const String name = "onPlaylist";
}

class ReadyEvent implements PlayerEvent {
  final double setupTime;

  ReadyEvent(Map<String, dynamic> event)
      : setupTime = event["setupTime"] ?? 0.0;
  static const String name = "onReady";
}

class SeekEvent implements PlayerEvent {
  final double position;
  final double offset;
  SeekEvent(Map<String, dynamic> event)
      : position = event["position"] ?? 0.0,
        offset = event["offset"] ?? 0.0;
  static const String name = "onSeek";
}

class SeekedEvent implements PlayerEvent {
  final double position;
  SeekedEvent(Map<String, dynamic> event) : position = event["position"] ?? 0.0;
  static const String name = "onSeeked";
}

class SetupErrorEvent implements PlayerEvent {
  final String message;
  SetupErrorEvent(Map<String, dynamic> event) : message = event["message"];
  static const String name = "onSetupError";
}

class TimeEvent implements PlayerEvent {
  final double duration;
  final double position;
  TimeEvent(Map<String, dynamic> event)
      : duration = event["duration"],
        position = event["position"];
  static const String name = "onTime";
}

class VisualQualityEvent implements PlayerEvent {
  static const String name = "onVisualQuality";
}

class AdBreakStartEvent implements PlayerEvent {
  static const String name = "onAdBreakStart";
}

class AdBreakEndEvent implements PlayerEvent {
  static const String name = "onAdBreakEnd";
}
