package com.ifenghui.commonlibrary.utils

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import java.io.IOException
import java.lang.Exception

@Suppress("DEPRECATION")
class VoicePromptUtils {

    private var mMediaPlayer: MediaPlayer? = null
    private var BEEP_VOLUME = 1.0f

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: VoicePromptUtils? = null


        /**
         * 获取实例
         */
        @JvmStatic
        fun getInstance(): VoicePromptUtils {
            if (INSTANCE == null) {
                synchronized(VoicePromptUtils::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = VoicePromptUtils()
                    }
                }
            }
            return INSTANCE ?: VoicePromptUtils()
        }
    }

    /**
     * 播放语音提示
     */
    fun playVoiceTips(context: Context, voiceResource: Int, callback: Callback<Any>?) {
        try {
            if (mMediaPlayer == null) {
                mMediaPlayer = MediaPlayer()
                mMediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)
            }
            mMediaPlayer?.pause()
            mMediaPlayer?.reset()
            mMediaPlayer?.setOnCompletionListener {
                callback?.call(0)
            }
            mMediaPlayer?.setOnPreparedListener {
                mMediaPlayer?.start()
            }
            val file = context.resources.openRawResourceFd(voiceResource)

            mMediaPlayer?.setDataSource(file.fileDescriptor, file.startOffset, file.length)
            file.close()
            mMediaPlayer?.setVolume(BEEP_VOLUME, BEEP_VOLUME)
            mMediaPlayer?.prepareAsync()
        } catch (e: IOException) {
            mMediaPlayer = null
        } catch (e: Exception) {
            mMediaPlayer = null
        }
    }

    /**
     * 暂停播放
     */
    fun pauseMedia() {
        try {
            mMediaPlayer?.pause()
        } catch (e: Exception) {
        }
    }

    /**
     * 重置音量
     */
    fun resetMediaVolume(volume: Float) {
        try {
            BEEP_VOLUME = volume
            mMediaPlayer?.setVolume(BEEP_VOLUME, BEEP_VOLUME)
        } catch (e: Exception) {
        }
    }

    /**
     * 释放播放器资源
     */
    fun releasePrompt() {
        try {
            mMediaPlayer?.stop()
            mMediaPlayer?.release()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                mMediaPlayer?.clearOnSubtitleDataListener()
                mMediaPlayer?.clearOnMediaTimeDiscontinuityListener()
            }
        } catch (e: Exception) {
        }
        mMediaPlayer = null
        INSTANCE = null
        System.gc()
    }
}