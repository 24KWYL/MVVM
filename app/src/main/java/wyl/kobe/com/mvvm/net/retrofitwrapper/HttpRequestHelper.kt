package wyl.kobe.com.mvvm.net.retrofitwrapper

import android.content.Context

import rx.Observable
import rx.Subscriber
import wyl.kobe.com.mvvm.net.api.RetrofitService


abstract class HttpRequestHelper(context: Context)
//        if(context != null){ //将每一个subscriber放入基础activity中  统一解绑
//            ((AppCompatActivity)context).addSubscriber(getSubscriber());
//        }
{

    //获取观察者(得到请求数据后的操作)
    abstract val subscriber: Subscriber<Any>

    //获取RetrofitService中的具体Observable
    abstract fun getObservable(retrofitService: RetrofitService): Observable<Any>
}
