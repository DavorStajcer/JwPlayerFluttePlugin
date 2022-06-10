import 'package:flutter/services.dart';

abstract class PlayerChannel {
  static MethodChannel getMethodChannelForView(int viewId) =>
      MethodChannel("tv.mta.jwplayer/JWPlayerView_MethodChannel_$viewId");

  static EventChannel getEventChannelForView(int viewId) =>
      EventChannel("tv.mta.jwplayer/JWPlayerView_EventChannel_$viewId");
}
