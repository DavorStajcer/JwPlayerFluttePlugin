import 'package:flutter/material.dart';
import 'package:jw_player_flutter/player/media_player.dart';
import 'package:jw_player_flutter/player/video_player_config.dart';

class PlayerWidgetManager {
  final Map<String, MediaPlayer> activePlayers = {};

  getVideo(String videoId, VideoPlayerConfig videoPlayerConfig) {
    if (activePlayers.containsKey(videoId)) {
      return activePlayers[videoId];
    }
    return _createNewPlayer(videoId, videoPlayerConfig);
  }

  MediaPlayer _createNewPlayer(
      String playerId, VideoPlayerConfig videoPlayerConfig) {
    activePlayers[playerId] = MediaPlayer(
      key: Key(playerId),
      videoPlayerConfig: videoPlayerConfig,
      seek: 0,
    );
    return activePlayers[playerId]!;
  }

  onPlayerDisposed(String playerId) => activePlayers.remove(playerId);
}
