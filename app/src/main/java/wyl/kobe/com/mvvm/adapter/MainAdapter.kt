package wyl.kobe.com.mvvm.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import wyl.kobe.com.mvvm.R
import wyl.kobe.com.mvvm.bean.JsonBean

class MainAdapter(val context: Context, var jsonBean: JsonBean?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_imageview, p0,false))
    }

    override fun getItemCount(): Int {
        return if (jsonBean?.data!!.isNotEmpty()) jsonBean?.data!!.size else 0
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        Glide.with(context).load(jsonBean?.data!![p1].imagePath).into((p0 as? MyViewHolder)?.imageView)
    }


    class MyViewHolder(parentView: View) : RecyclerView.ViewHolder(parentView) {
        var imageView: ImageView? = null

        init {
            imageView = parentView.findViewById(R.id.image)
        }
    }

}