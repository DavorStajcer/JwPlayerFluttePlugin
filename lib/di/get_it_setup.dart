import 'package:get_it/get_it.dart';
import 'package:jw_player_flutter/miniplayer_example/miniplayer_cubit.dart';
import 'package:jw_player_flutter/miniplayer_example/player_widget_manager.dart';
import 'package:jw_player_flutter/player/player_channel_manager.dart';

final getIt = GetIt.I;

setUpGetIt() {
  getIt.registerSingleton<PlayerWidgetManager>(PlayerWidgetManager());
  getIt.registerSingleton<MiniplayerCubit>(MiniplayerCubit());
  getIt.registerFactory(() => PlayerChannelManagerImpl());
}
