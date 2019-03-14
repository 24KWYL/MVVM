package wyl.kobe.com.mvvm

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.media.MediaPlayer

/**
 * 播放器管理
 */
class MediaPlayerObserver(var mediaPlayer: MediaPlayer?) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun initMediaPlayer() {
        try {
            mediaPlayer = MediaPlayer()
            mediaPlayer?.setDataSource("")
            mediaPlayer?.prepareAsync()
        } catch (e: Exception) {
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startMediaPlayer() {
        mediaPlayer?.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopMediaPlayer() {
        mediaPlayer?.stop()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun releaseMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
