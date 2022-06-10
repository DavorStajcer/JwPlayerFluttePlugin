import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:jw_player_flutter/player/player_arguments.dart';
import 'package:jw_player_flutter/player/video_player_config.dart';

import 'player_observer.dart';

class MediaPlayer extends StatefulWidget {
  final VideoPlayerConfig videoPlayerConfig;
  final double seek;

  const MediaPlayer({
    required this.videoPlayerConfig,
    required this.seek,
    Key? key,
  }) : super(key: key);

  @override
  MediaPlayerState createState() => MediaPlayerState();
}

class MediaPlayerState extends State<MediaPlayer> {
  final PlayerEventManager playerObserver = PlayerEventManager.instance();
  int? _platformViewId;

  @override
  void didUpdateWidget(MediaPlayer oldWidget) {
    super.didUpdateWidget(oldWidget);
  }

  @override
  void dispose() {
    _disposePlatformView(_platformViewId, isDisposing: true);
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    playerObserver.isAutoPlay = widget.videoPlayerConfig.autoplay;

    if (Platform.isAndroid) {
      AspectRatio(
        aspectRatio: 16 / 9,
        child: AndroidView(
          key: widget.key,
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
            PlayerArguments.autoPlay: widget.videoPlayerConfig.autoplay,
            PlayerArguments.file: widget.videoPlayerConfig.file,
            PlayerArguments.adTags: widget.videoPlayerConfig.videoAdConfigs,
            PlayerArguments.seek: widget.seek,
          },
          creationParamsCodec: const JSONMessageCodec(),
          onPlatformViewCreated: (viewId) {
            _platformViewId = viewId;
            playerObserver.listen(_onFirstFrame, _onPlayerError);
          },
        ),
      );
    } else if (Platform.isIOS) {
      AspectRatio(
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
            "autoPlay": widget.videoPlayerConfig.autoplay,
            "file": widget.videoPlayerConfig.file,
          },
          creationParamsCodec: const JSONMessageCodec(),
          onPlatformViewCreated: (viewId) {
            _platformViewId = viewId;
            playerObserver.listen(_onFirstFrame, _onPlayerError);
          },
        ),
      );
    }

    return AspectRatio(
      aspectRatio: 16 / 9,
      child: Container(
        color: Colors.black87,
      ),
    );
  }

  void _onFirstFrame() async {
    /* first frame */
  }

  void _onPlayerError() async {
    /* player error */
  }

  void _disposePlatformView(int? viewId, {bool isDisposing = false}) {
    if (viewId != null) {
      var methodChannel = MethodChannel("tv.mta.jwplayer/JWPlayerView_$viewId");

      PlayerEventManager.instance().onMiniplayerDispose();

      /* clean platform view */
      methodChannel.invokeMethod("dispose");

      if (!isDisposing) {
        setState(() {
          _platformViewId = null;
        });
      }
    }
  }
}
