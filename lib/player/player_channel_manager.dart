import 'dart:async';

import 'package:flutter/services.dart';
import 'package:jw_player_flutter/player/channels.dart';
import 'package:jw_player_flutter/player/event_arguments.dart';
import 'package:rxdart/subjects.dart';

abstract class PlayerChannelManager {
  Stream<PlayerEvent> get miniplayerPlayingStream;

  Stream<PlayerEvent> get allEventsStream;

  Stream<PlayerEvent> get adEventsStream;

  EventChannel? get eventChannel;

  MethodChannel? get methodChannel;

  void onPlatformViewCreated(int viewId);

  void onPlayerDisposed();
}

class PlayerChannelManagerImpl implements PlayerChannelManager {
  StreamSubscription? _eventStreamSubscription;

  EventChannel? _eventChannel;
  @override
  EventChannel? get eventChannel => _eventChannel;
  MethodChannel? _methodChannel;
  @override
  MethodChannel? get methodChannel => _methodChannel;
  final PublishSubject<PlayerEvent> _miniplayerPlayingSubject =
      PublishSubject();
  @override
  Stream<PlayerEvent> get miniplayerPlayingStream =>
      _miniplayerPlayingSubject.stream;
  final PublishSubject<PlayerEvent> _allEventsSubject = PublishSubject();
  @override
  Stream<PlayerEvent> get allEventsStream => _allEventsSubject.stream;
  final PublishSubject<PlayerEvent> _adEventsSubject = PublishSubject();
  @override
  Stream<PlayerEvent> get adEventsStream => _adEventsSubject.stream;

  @override
  void onPlatformViewCreated(int viewId) async {
    _eventChannel = PlayerChannel.getEventChannelForView(viewId);
    _eventStreamSubscription =
        eventChannel?.receiveBroadcastStream().listen(processEvent);

    _methodChannel = PlayerChannel.getMethodChannelForView(viewId);
  }

  @override
  void onPlayerDisposed() {
    _methodChannel?.invokeMapMethod("dispose");
    _eventStreamSubscription?.cancel();
  }

  void processEvent(dynamic event) async {
    String eventName = event["name"];
    switch (eventName) {
      case AdCompleteEvent.name:
        _allEventsSubject.add(AdCompleteEvent());
        break;
      case AdErrorEvent.name:
        final adEvent = AdErrorEvent();
        _allEventsSubject.add(adEvent);
        _adEventsSubject.add(adEvent);
        break;
      case AdImpressionEvent.name:
        final adEvent = AdImpressionEvent();

        _allEventsSubject.add(adEvent);
        _adEventsSubject.add(adEvent);
        break;
      case AdPauseEvent.name:
        final adEvent = AdPauseEvent();

        _allEventsSubject.add(adEvent);
        _adEventsSubject.add(adEvent);
        break;
      case AdPlayEvent.name:
        final adEvent = AdPlayEvent();

        _allEventsSubject.add(adEvent);
        _adEventsSubject.add(adEvent);
        break;
      case AdScheduleEvent.name:
        final adEvent = AdScheduleEvent();

        _allEventsSubject.add(adEvent);
        _adEventsSubject.add(adEvent);
        break;

      case AdSkipEvent.name:
        final adEvent = AdSkipEvent();

        _allEventsSubject.add(adEvent);
        _adEventsSubject.add(adEvent);
        break;

      case AdTimeEvent.name:
        final adEvent = AdTimeEvent(event);
        _allEventsSubject.add(adEvent);
        _adEventsSubject.add(adEvent);
        _miniplayerPlayingSubject.add(adEvent);
        break;

      case BeforeCompleteEvent.name:
        _allEventsSubject.add(BeforeCompleteEvent());
        break;

      case BeforePlayEvent.name:
        _allEventsSubject.add(BeforePlayEvent());
        break;

      case AudioTracksEvent.name:
        _allEventsSubject.add(AudioTracksEvent());
        break;

      case CaptionsChangedEvent.name:
        _allEventsSubject.add(CaptionsChangedEvent(event));
        break;
      case CaptionsListEvent.name:
        _allEventsSubject.add(CaptionsListEvent());
        break;
      case BufferEvent.name:
        _allEventsSubject.add(BufferEvent());
        break;
      case CompleteEvent.name:
        _allEventsSubject.add(CompleteEvent());
        break;
      case ControlBarVisibilityChangedEvent.name:
        _allEventsSubject.add(ControlBarVisibilityChangedEvent(event));
        break;
      case ControlsEvent.name:
        _allEventsSubject.add(ControlsEvent(event));
        break;
      case DisplayClickEvent.name:
        _allEventsSubject.add(DisplayClickEvent());
        break;
      case ErrorEvent.name:
        _allEventsSubject.add(ErrorEvent(event));
        break;
      case FirstFrameEvent.name:
        _allEventsSubject.add(FirstFrameEvent(event));
        break;
      case FullscreenEvent.name:
        _allEventsSubject.add(FullscreenEvent(event));
        break;
      case IdleEvent.name:
        _allEventsSubject.add(IdleEvent());
        break;
      case LevelsChangedEvent.name:
        _allEventsSubject.add(LevelsChangedEvent());
        break;
      case LevelsEvent.name:
        _allEventsSubject.add(LevelsEvent());
        break;
      case MetaEvent.name:
        _allEventsSubject.add(MetaEvent());
        break;
      case MuteEvent.name:
        _allEventsSubject.add(MuteEvent(event));
        break;
      case PauseEvent.name:
        _allEventsSubject.add(PauseEvent());
        break;
      case PlayEvent.name:
        _allEventsSubject.add(PlayEvent());
        break;
      case PlaylistCompleteEvent.name:
        _allEventsSubject.add(PlaylistCompleteEvent());
        break;
      case PlaylistItemEvent.name:
        _allEventsSubject.add(PlaylistItemEvent());
        break;
      case PlaylistEvent.name:
        _allEventsSubject.add(PlaylistEvent());
        break;
      case ReadyEvent.name:
        _allEventsSubject.add(ReadyEvent(event));
        break;
      case SeekEvent.name:
        _allEventsSubject.add(SeekEvent(event));
        break;
      case SeekedEvent.name:
        _allEventsSubject.add(SeekedEvent(event));
        break;
      case SetupErrorEvent.name:
        _allEventsSubject.add(SetupErrorEvent(event));
        break;
      case TimeEvent.name:
        final newEvent = TimeEvent(event);
        _allEventsSubject.add(newEvent);
        _miniplayerPlayingSubject.add(newEvent);
        break;
      case VisualQualityEvent.name:
        _allEventsSubject.add(VisualQualityEvent());
        break;
      case AdBreakStartEvent.name:
        _allEventsSubject.add(AdBreakStartEvent());
        break;
      case AdBreakEndEvent.name:
        _allEventsSubject.add(AdBreakEndEvent());
        break;
    }
  }
}
