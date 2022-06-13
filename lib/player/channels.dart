import 'package:flutter/services.dart';

abstract class PlayerChannel {
  static MethodChannel getMethodChannelForView(int viewId) =>
      MethodChannel("tv.mta.jwplayer/JWPlayerView_MethodChannel_$viewId");

  static MethodChannel getMethodChannelForLog() =>
      const MethodChannel("tv.mta.jwplayer/JWPlayerView_MethodChannel_log");

  static EventChannel getEventChannelForView(int viewId) =>
      EventChannel("tv.mta.jwplayer/JWPlayerView_EventChannel_$viewId");
}
