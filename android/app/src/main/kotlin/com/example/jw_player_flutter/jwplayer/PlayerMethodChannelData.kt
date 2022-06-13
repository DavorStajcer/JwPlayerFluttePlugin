package com.example.jw_player_flutter.jwplayer

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject
import java.lang.RuntimeException

class PlayerMethodChannelData(
    var file: String?,
    var autoPlay: Boolean,
    var adConfigs: List<VideoAdConfig>,
    var seek: Double
) {


    companion object {
        fun createFromMethodChannelObject(arguments: Any?): PlayerMethodChannelData {
            Log.v(LogTags.DEBUG,arguments.toString())
            val args = arguments as JSONObject?
            val file = getFile(args)
            val autoPlay = getAutoPlay(args)
            val jsonStringAdConfigs = getJSONStringAdConfigs(args)
            val listType = object : TypeToken<List<VideoAdConfig?>?>() {}.type
            var videoAdConfigs: List<VideoAdConfig> = ArrayList()
            if (jsonStringAdConfigs != null) {
                videoAdConfigs = try {
                    Gson().fromJson(jsonStringAdConfigs, listType)
                }catch (e : RuntimeException){
                    listOf()
                }
            }
            val seek = getSeek(args)
            return PlayerMethodChannelData(file, autoPlay, videoAdConfigs, seek)
        }

        private fun getFile(args: JSONObject?): String? {
            return if (args == null) {
                null
            } else try {
                args.getString(PlayerArguments.FILE)
            } catch (e: JSONException) {
                null
            }
        }

        private fun getAutoPlay(args: JSONObject?): Boolean {
            return if (args == null) {
                false
            } else try {
                args.getBoolean(PlayerArguments.AUTO_PLAY)
            } catch (e: JSONException) {
                false
            }
        }

        private fun getJSONStringAdConfigs(args: JSONObject?): String? {
            return if (args == null) {
                null
            } else try {
                args.getString(PlayerArguments.AD_TAGS)
            } catch (e: JSONException) {
                null
            }
        }

        private fun getSeek(args: JSONObject?): Double {
            return if (args == null) {
                0.0
            } else try {
                args.getDouble(PlayerArguments.SEEK)
            } catch (e: JSONException) {
                0.0
            }
        }
    }
}