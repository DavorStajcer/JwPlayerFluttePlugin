import 'package:flutter/services.dart';
import 'package:jw_player_flutter/player/player_observer.dart';

abstract class PlatformViewDisposer {
  int? _platformViewId;

  void disposePlatfromView() {
    if (_platformViewId != null) {
      var methodChannel =
          MethodChannel("tv.mta.jwplayer/JWPlayerView_$_platformViewId");
      PlayerEventManager.instance().onMiniplayerDispose();
      methodChannel.invokeMethod("dispose");
    }
  }

  void onPlatformViewCreated(int platformViewId) =>
      _platformViewId = platformViewId;
}
