package wyl.kobe.com.mvvm.net.retrofitwrapper


import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import wyl.kobe.com.mvvm.net.api.RetrofitService

class RetrofitRequest private constructor() {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .client(RetrofitUtil.genericClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(RetrofitUtil.baseUrl)
                .build()
    }
    val retrofitService: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }


    companion object {
        val instance: RetrofitRequest by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { RetrofitRequest() }
    }

    /**
     * post、get等请求
     *
     * @param
     */
    fun doHttps(observable: Observable<Any>, subscriber: Subscriber<Any>) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object :Subscriber<Any>(){
//                    override fun onNext(t: Any?) {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun onCompleted() {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun onError(e: Throwable?) {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    }
//
//                })
                .subscribe(subscriber)
    }


}