import 'package:flutter/services.dart';
import 'package:jw_player_flutter/player/event_arguments.dart';
import 'package:rxdart/subjects.dart';

class PlayerEventManager {
  final EventChannel? eventChannel = null;

  final PublishSubject<bool> _miniplayerPlaying = PublishSubject();
  Stream<bool> get miniplayerPlayingStream => _miniplayerPlaying.stream;

  final PublishSubject<PlayerEvent> _events = PublishSubject();
  Stream<PlayerEvent> get eventStream => _events.stream;

  void onPlatformViewCreated(int viewId) async {
    EventChannel("tv.mta.jwplayer/JWPlayerView_$viewId")
        .receiveBroadcastStream()
        .listen(processEvent);
  }

  onMiniplayerDispose() {
    //_miniplayerPlaying.close();
  }

  void processEvent(dynamic event) async {
    String eventName = event["name"];
    print('Event received -> $eventName');
    switch (eventName) {
      case AdCompleteEvent.name:
        _events.add(AdCompleteEvent());
        break;
      case AdErrorEvent.name:
        _events.add(AdErrorEvent());
        break;
      case AdImpressionEvent.name:
        _events.add(AdImpressionEvent());
        break;
      case AdPauseEvent.name:
        _events.add(AdPauseEvent());
        break;
      case AdPlayEvent.name:
        _events.add(AdPlayEvent());
        break;
      case AdScheduleEvent.name:
        _events.add(AdScheduleEvent());
        break;

      case AdSkipEvent.name:
        _events.add(AdSkipEvent());
        break;

      case AdTimeEvent.name:
        _events.add(AdTimeEvent(event));
        break;

      case BeforeCompleteEvent.name:
        _events.add(BeforeCompleteEvent());
        break;

      case BeforePlayEvent.name:
        _events.add(BeforePlayEvent());
        break;

      case AudioTracksEvent.name:
        _events.add(AudioTracksEvent());
        break;

      case CaptionsChangedEvent.name:
        _events.add(CaptionsChangedEvent(event));
        break;
      case CaptionsListEvent.name:
        _events.add(CaptionsListEvent());
        break;
      case BufferEvent.name:
        _events.add(BufferEvent());
        break;
      case CompleteEvent.name:
        _events.add(CompleteEvent());
        break;
      case ControlBarVisibilityChangedEvent.name:
        _events.add(ControlBarVisibilityChangedEvent(event));
        break;
      case ControlsEvent.name:
        _events.add(ControlsEvent(event));
        break;
      case DisplayClickEvent.name:
        _events.add(DisplayClickEvent());
        break;
      case ErrorEvent.name:
        _events.add(ErrorEvent(event));
        break;
      case FirstFrameEvent.name:
        _events.add(FirstFrameEvent(event));
        break;
      case FullscreenEvent.name:
        _events.add(FullscreenEvent(event));
        break;
      case IdleEvent.name:
        _events.add(IdleEvent());
        break;
      case LevelsChangedEvent.name:
        _events.add(LevelsChangedEvent());
        break;
      case LevelsEvent.name:
        _events.add(LevelsEvent());
        break;
      case "onReady":
        num setupTime = event["setupTime"];
        break;

      case "onAdTime":
        _miniplayerPlaying.add(true);
        break;
      case "onTime":
        print('miniplayer');
        event[""];
        _miniplayerPlaying.add(true);
        break;

      /* onBeforePlay */
      case "onBeforePlay":
        break;

      /* onFirstFrame */
      case "onFirstFrame":
        break;

      /* onPause */
      case "onPause":
        break;

      /* onPlayEvent */
      case "onPlayEvent":
        break;

      /* onComplete */
      case "onComplete":
        break;

      /* onTime */
      case "onTime":
        double position = (event["position"].toDouble()).abs();
        break;

      /* onSeek */
      case "onSeek":

        /* position of the player before the player seeks (in seconds) */
        int position = (event["position"]).toInt();

        /* requested position to seek to (in seconds) */
        double offset = double.parse("${event["offset"]}");

        /* do something */

        break;

      case "onError":
        /* do something */
        break;

      default:
        break;
    }
  }
}
