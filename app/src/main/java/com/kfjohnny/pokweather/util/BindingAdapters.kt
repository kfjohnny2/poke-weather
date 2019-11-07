package com.kfjohnny.pokweather.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.databinding.BindingAdapter
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.*
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kfjohnny.pokweather.util.helpers.AdapterItemsContract

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?: View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: LiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@BindingAdapter("background")
fun setBackgroundColor(view: LinearLayout, text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.setBackgroundColor(Color.parseColor(value?:""))})
    }
}

@BindingAdapter("textColor")
fun setTextColor(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.setTextColor(Color.parseColor(value))})
    }
}

@BindingAdapter("glideSrc")
fun setGlideSrc(view: ImageView, text: String?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        Glide.with(view.context).load(text).into(view)
    }
}

@BindingAdapter("adapter")
fun setAdapter(recyclerView: RecyclerView, items: List<Any>) {
    recyclerView.adapter.let {
        if (it is AdapterItemsContract) {
            it.replaceItems(items)
        }
    }

}