package wyl.kobe.com.mvvm.net.retrofitwrapper

import android.content.Context

import java.lang.ref.WeakReference

import rx.Subscriber


/**
 * subscriber封装 用于Loading显示隐藏
 *
 * @param <T>
</T> */
abstract class MySubscriber<T> : Subscriber<T> {
    private var isShowProgress = true
    private var contextWeakReference: WeakReference<Context>? = null
    private var context: Context? = null

    constructor(context: Context) {
        this.contextWeakReference = WeakReference(context)
        init(false)
    }

    constructor(context: Context, isShowProgress: Boolean) {
        this.isShowProgress = isShowProgress
        this.contextWeakReference = WeakReference(context)
        init(false)
    }

    constructor(context: Context, isShowProgress: Boolean, isCanCancel: Boolean) {
        this.isShowProgress = isShowProgress
        this.contextWeakReference = WeakReference(context)
        init(isCanCancel)
    }

    private fun init(isCanCancel: Boolean) {
        context = contextWeakReference!!.get()
    }


    override fun onCompleted() {
        onFinish()
        doUnsubscribe()
    }


    override fun onError(e: Throwable) {
        onFinish()
        doUnsubscribe()
    }

    fun onFinish() {}

    override fun onStart() {
        super.onStart()
    }

    //解绑
    fun doUnsubscribe() {
        if (!isUnsubscribed) {
            unsubscribe()
        }
    }
}
