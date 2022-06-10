import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:jw_player_flutter/di/get_it_setup.dart';
import 'package:jw_player_flutter/miniplayer_example/miniplayer_cubit.dart';
import 'package:jw_player_flutter/miniplayer_example/player_widget_manager.dart';
import 'package:jw_player_flutter/player/ad_offset.dart';
import 'package:jw_player_flutter/player/player_observer.dart';
import 'package:jw_player_flutter/player/video_ad_config.dart';
import 'package:jw_player_flutter/player/video_player_config.dart';

void main() {
  setUpGetIt();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'JWPlayer Flutter',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const Player(title: 'JWPlayer Flutter'),
    );
  }
}

class Player extends StatefulWidget {
  const Player({
    Key? key,
    required this.title,
  }) : super(key: key);

  final String title;

  @override
  State<Player> createState() => _PlayerState();
}

class _PlayerState extends State<Player> {
  static const adIMATagUrl =
      "https://pubads.g.doubleclick.net/gampad/ads?iu=/21775744923/external/single_ad_samples&sz=640x480&cust_params=sample_ct%3Dlinear&ciu_szs=300x250%2C728x90&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=";

  static List<VideoAdConfig> adConfigs = [
    VideoAdConfig(
      adIMATagUrl,
      AdOffset.preroll(),
    ),
    VideoAdConfig(
      adIMATagUrl,
      AdOffset.postroll(),
    ),
    VideoAdConfig(
      adIMATagUrl,
      AdOffset.custom(seconds: 6),
    ),
  ];

  final videoPlayerConfig = VideoPlayerConfig(
    file: "https://content.jwplatform.com/manifests/yp34SRmf.m3u8",
    autoplay: true,
    videoAdConfigs: adConfigs,
  );

  final ScrollController scrollController = ScrollController();

  @override
  void initState() {
    super.initState();

    PlayerEventManager.instance().miniplayerPlayingStream.listen((isPlaying) {
      if (scrollController.offset > 300 &&
          getIt<MiniplayerCubit>().state is MiniplayerNotShowed) {
        final Widget player = getIt<PlayerWidgetManager>().getVideo(
          "1",
          videoPlayerConfig,
        );
        getIt<MiniplayerCubit>().showMiniplayer(player);
      }
    });

    scrollController.addListener(() {
      if (scrollController.offset < 300 &&
          getIt<MiniplayerCubit>().state is MiniplayerShowed) {
        getIt<MiniplayerCubit>().hideMiniplayer();
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          CustomScrollView(
            controller: scrollController,
            slivers: [
              SliverToBoxAdapter(
                child: BlocBuilder<MiniplayerCubit, MiniplayerState>(
                  bloc: getIt<MiniplayerCubit>(),
                  builder: (context, state) => state is MiniplayerNotShowed
                      ? getIt<PlayerWidgetManager>().getVideo(
                          "1",
                          videoPlayerConfig,
                        )
                      : AspectRatio(
                          aspectRatio: 16 / 9,
                          child: Container(
                            color: Colors.black45,
                            child: const Center(
                              child: Text("Playing in miniplayer"),
                            ),
                          ),
                        ),
                ),
              ),
              SliverToBoxAdapter(
                child: Container(
                  color: Colors.grey,
                  height: 2000,
                ),
              )
            ],
          ),
          BlocBuilder<MiniplayerCubit, MiniplayerState>(
            bloc: getIt<MiniplayerCubit>(),
            builder: (context, state) {
              return state is MiniplayerShowed
                  ? Positioned(
                      bottom: 30,
                      right: 20,
                      child: ClipRRect(
                        key: UniqueKey(),
                        borderRadius: BorderRadius.circular(8),
                        child: SizedBox(
                          width: 192,
                          height: 107,
                          child: state.player,
                        ),
                      ),
                    )
                  : const SizedBox();
            },
          )
        ],
      ),
    );
  }
}
