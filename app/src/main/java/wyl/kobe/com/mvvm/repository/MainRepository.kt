package wyl.kobe.com.mvvm.repository

import android.arch.lifecycle.MutableLiveData
import wyl.kobe.com.mvvm.bean.JsonBean
import wyl.kobe.com.mvvm.net.retrofitwrapper.RetrofitRequest
import wyl.kobe.com.mvvm.parse

class MainRepository {

    fun getDataFromServer(data: MutableLiveData<JsonBean>) {
        RetrofitRequest.instance.retrofitService.json().parse {
            data.value = it
        }
    }
}