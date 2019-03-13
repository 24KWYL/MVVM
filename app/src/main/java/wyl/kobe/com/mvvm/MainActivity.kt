package wyl.kobe.com.mvvm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import wyl.kobe.com.mvvm.adapter.MainAdapter
import wyl.kobe.com.mvvm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
    }

    private fun initData() {
        mModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        mModel.data?.observe(this, Observer {
            val mainAdapter = MainAdapter(this, it)
            val linearLayoutManager = LinearLayoutManager(this)
            rv.layoutManager = linearLayoutManager
            rv.adapter = mainAdapter
        })
        mModel.getDataFromServer()

    }
}
