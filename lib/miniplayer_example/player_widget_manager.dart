import 'package:flutter/material.dart';
import 'package:jw_player_flutter/player/media_player.dart';
import 'package:jw_player_flutter/player/player_channel_manager.dart';
import 'package:jw_player_flutter/player/video_player_config.dart';
import 'package:provider/provider.dart';

class PlayerWidgetManager {
  final Map<String, Widget> activePlayers = {};

  getVideo(String videoId, VideoPlayerConfig videoPlayerConfig,
      PlayerChannelManager manager) {
    if (activePlayers.containsKey(videoId)) {
      return activePlayers[videoId];
    }
    return _createNewPlayer(videoId, videoPlayerConfig, manager);
  }

  Widget _createNewPlayer(String playerId, VideoPlayerConfig videoPlayerConfig,
      PlayerChannelManager manager) {
    activePlayers[playerId] = Provider<PlayerChannelManager>(
      create: (conte_xt) => manager,
      child: MediaPlayer(
        key: Key(playerId),
        videoPlayerConfig: videoPlayerConfig,
        seek: 0,
      ),
    );
    return activePlayers[playerId]!;
  }

  onPlayerDisposed(String playerId) => activePlayers.remove(playerId);
}
