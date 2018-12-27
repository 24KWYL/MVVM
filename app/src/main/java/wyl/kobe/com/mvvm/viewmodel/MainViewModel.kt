package wyl.kobe.com.mvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import wyl.kobe.com.mvvm.bean.JsonBean
import wyl.kobe.com.mvvm.repository.MainRepository

class MainViewModel : ViewModel() {
    private val repertory: MainRepository by lazy { MainRepository() }
    var data: MutableLiveData<JsonBean> = MutableLiveData()

    fun getDataFromServer(){
        repertory.getDataFromServer(data)
    }
}