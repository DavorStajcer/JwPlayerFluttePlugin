import 'package:jw_player_flutter/player/video_ad_config.dart';

class VideoPlayerConfig {
  final String file;
  final bool autoplay;
  final List<VideoAdConfig> videoAdConfigs;

  const VideoPlayerConfig({
    required this.file,
    required this.videoAdConfigs,
    required this.autoplay,
  });

  Map<String, dynamic> toJson() => {
        "file": file,
        "autoplay": autoplay,
        "videoAdConfigs":
            videoAdConfigs.map((config) => config.toJson()).toList(),
      };

  VideoPlayerConfig fromJson(Map<String, dynamic> json) => VideoPlayerConfig(
        file: json["file"],
        videoAdConfigs: (json["videoAdConfigs"] as List<dynamic>?)
                ?.map((configJson) => VideoAdConfig.fromJson(configJson))
                .toList() ??
            [],
        autoplay: json["autoplay"],
      );
}
