import 'dart:developer';
import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:jw_player_flutter/player/player_arguments.dart';
import 'package:jw_player_flutter/player/video_player_config.dart';
import 'package:provider/provider.dart';

import 'player_channel_manager.dart';

class MediaPlayer extends StatelessWidget {
  final VideoPlayerConfig videoPlayerConfig;
  final double seek;

  const MediaPlayer({
    required this.videoPlayerConfig,
    required this.seek,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    if (Platform.isAndroid) {
      log("building nedia playerrr");
      return AspectRatio(
        aspectRatio: 16 / 9,
        child: AndroidView(
          key: key,
          viewType: '<jw-player-view>',
          layoutDirection: TextDirection.ltr,
          gestureRecognizers: Set()
            ..add(
              Factory<HorizontalDragGestureRecognizer>(
                () => HorizontalDragGestureRecognizer(),
              ),
            )
            ..add(
              Factory<TapGestureRecognizer>(
                () => TapGestureRecognizer(),
              ),
            ),
          creationParams: {
            PlayerArguments.autoPlay: videoPlayerConfig.autoplay,
            PlayerArguments.file: videoPlayerConfig.file,
            PlayerArguments.adTags: videoPlayerConfig.videoAdConfigs,
            PlayerArguments.seek: seek,
          },
          creationParamsCodec: const JSONMessageCodec(),
          onPlatformViewCreated: (viewId) {
            log('Platforrm view created for $viewId');
            Provider.of<PlayerChannelManager>(context, listen: false)
                .onPlatformViewCreated(viewId);
          },
        ),
      );
    } else if (Platform.isIOS) {
      return AspectRatio(
        aspectRatio: 16 / 9,
        child: UiKitView(
          viewType: 'tv.mta.jwplayer/JWPlayerView',
          gestureRecognizers: Set()
            ..add(
              Factory<HorizontalDragGestureRecognizer>(
                () => HorizontalDragGestureRecognizer(),
              ),
            )
            ..add(
              Factory<TapGestureRecognizer>(
                () => TapGestureRecognizer(),
              ),
            ),
          creationParams: {
            PlayerArguments.autoPlay: videoPlayerConfig.autoplay,
            PlayerArguments.file: videoPlayerConfig.file,
            PlayerArguments.adTags: videoPlayerConfig.videoAdConfigs,
            PlayerArguments.seek: seek,
          },
          creationParamsCodec: const JSONMessageCodec(),
          onPlatformViewCreated: (viewId) {
            log("platform view created $viewId");
            Provider.of<PlayerChannelManager>(context)
                .onPlatformViewCreated(viewId);
          },
        ),
      );
    }
    log('bulding nothing');
    return AspectRatio(
      aspectRatio: 16 / 9,
      child: Container(
        color: Colors.black87,
      ),
    );
  }
}
