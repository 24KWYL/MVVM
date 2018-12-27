package wyl.kobe.com.mvvm.net.api


import retrofit2.http.GET
import rx.Observable
import wyl.kobe.com.mvvm.bean.JsonBean

interface RetrofitService {

    @GET(Api.json)
    fun json(): Observable<JsonBean>

}
