package wyl.kobe.com.mvvm

import android.util.Log
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

fun <T> Observable<T>.parse(success: (T) -> Unit) {
    this.subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<T>() {
                override fun onNext(t: T) {
                    success(t)
                }

                override fun onCompleted() {
                }

                override fun onError(e: Throwable?) {
                }
            })
}
