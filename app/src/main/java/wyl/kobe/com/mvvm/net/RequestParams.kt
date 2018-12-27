package wyl.kobe.com.mvvm.net

import android.text.TextUtils


import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.io.Serializable
import java.util.HashMap

import okhttp3.MediaType
import okhttp3.RequestBody


/**
 * 用于RequestBody的Json参数封装
 */
class RequestParams : Serializable {

    val params = HashMap<String, String>()
    private val jsonObjectParams = HashMap<String, JSONObject>()
    private val jsonArrayParams = HashMap<String, JSONArray>()

    //获取所有数据 以json形式给出
    val paramsByJson: RequestBody?
        get() {
            var body: RequestBody? = null
            var jsonObject: JSONObject? = null
            if (params.size > 0) {
                jsonObject = JSONObject(params)
            }
            if (jsonObjectParams.size > 0) {
                if (jsonObject == null) {
                    jsonObject = JSONObject(jsonObjectParams)
                } else {
                    addMapToJsonObject(jsonObject, jsonObjectParams)
                }
            }
            if (jsonArrayParams.size > 0) {
                if (jsonObject == null) {
                    jsonObject = JSONObject(jsonArrayParams)
                } else {
                    addMapToJsonObject(jsonObject, jsonArrayParams)
                }
            }
            if (jsonObject != null) {
                val newJsonObject = JSONObject()
                try {
                    newJsonObject.put("data", jsonObject)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

                body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), newJsonObject.toString())
            }
            return body
        }

    constructor() {
        init()
    }

    constructor(key: String, value: String) {
        init()
        put(key, value)
    }


    fun clearParam() {
        params.clear()
        jsonObjectParams.clear()
        jsonArrayParams.clear()
    }

    private fun init() {
        params.clear()
        jsonObjectParams.clear()
        jsonArrayParams.clear()
    }

    fun put(key: String, value: Any?) {
        if (!TextUtils.isEmpty(key) && value != null) {
            params[key] = value.toString()
        }
    }


    fun putAll(par: RequestParams?) {
        if (par != null && par.params != null) {
            this.params.putAll(par.params)
        }
    }

    fun put(key: String, value: JSONObject?) {
        if (!TextUtils.isEmpty(key) && value != null) {
            jsonObjectParams[key] = value
        }
    }

    fun put(key: String, value: JSONArray?) {
        if (!TextUtils.isEmpty(key) && value != null) {
            jsonArrayParams[key] = value
        }
    }

    //将map加入jsonObject中
    private fun addMapToJsonObject(jsonObject: JSONObject, map: Map<*, *>) {
        for ((key1, value) in map) {
            val key = key1 as String
            if (!TextUtils.isEmpty(key)) {
                try {
                    jsonObject.put(key, value)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        }
    }

}
