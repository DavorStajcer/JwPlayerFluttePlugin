import 'package:flutter/material.dart';
import 'package:jw_player_flutter/player/media_player.dart';
import 'package:jw_player_flutter/player/video_player_config.dart';

class PlayerWidgetManager {
  final Map<String, MediaPlayer> activePlayers = {};

  getVideo(String videoId, VideoPlayerConfig videoPlayerConfig) {
    if (activePlayers.containsKey(videoId)) {
      print('Video getting: getting old one');
      return activePlayers[videoId];
    }
    print('Video getting: getting new one');
    return _createNewPlayer(videoId, videoPlayerConfig);
  }

  MediaPlayer _createNewPlayer(
      String videoId, VideoPlayerConfig videoPlayerConfig) {
    activePlayers[videoId] = MediaPlayer(
      key: Key(videoId),
      videoPlayerConfig: videoPlayerConfig,
      seek: 0,
    );
    return activePlayers[videoId]!;
  }
}
