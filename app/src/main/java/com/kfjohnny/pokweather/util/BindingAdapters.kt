package com.kfjohnny.pokweather.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.databinding.BindingAdapter
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kfjohnny.pokweather.util.extensions.getParentActivity
import com.kfjohnny.pokweather.util.extensions.onSearchTextChanged
import com.kfjohnny.pokweather.util.helpers.AdapterItemsContract

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(
            parentActivity,
            Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: LiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}

@BindingAdapter("background")
fun setBackgroundColor(view: LinearLayout, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(
            parentActivity,
            Observer { value -> view.setBackgroundColor(Color.parseColor(value ?: "")) })
    }
}

@BindingAdapter("textColor")
fun setTextColor(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(
            parentActivity,
            Observer { value -> view.setTextColor(Color.parseColor(value)) })
    }
}

@BindingAdapter("onImeOption")
fun setOnImeOption(view: EditText, func: Runnable) {
    view.setOnEditorActionListener { _, actionId, _ ->
        return@setOnEditorActionListener when (actionId) {
            EditorInfo.IME_ACTION_SEARCH -> {
                func.run()
                true
            }
            else -> false
        }
    }
}

/**
 * Binding function: Add textwatcher funcion on textChanged extension method
 *
 * @param view  EditText to add extension function
 * @param func  Function to run on textwatcher callback
 */
@BindingAdapter("addTextWatcherFunc")
fun setTextWatcher(view: EditText, func: Runnable) {
    view.onSearchTextChanged {
        func.run()
    }
}

/**
 * Binding function: Loading image url with glide library into image view
 *
 * @param view  ImageView to apply image
 * @param text  Image URL for loading src
 */
@BindingAdapter("glideSrc")
fun setGlideSrc(view: ImageView, text: String?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        Glide.with(view.context).load(text).diskCacheStrategy(DiskCacheStrategy.DATA).into(view)
    }
}

/**
 * Binding function: Items for replacing items into adapter
 *
 * @param recyclerView  RecyclerView to apply new items
 * @param items Items for replacing on recycler view adapter
 */
@BindingAdapter("adapter")
fun setAdapter(recyclerView: RecyclerView, items: List<Any>) {
    recyclerView.adapter.let {
        if (it is AdapterItemsContract) {
            it.replaceItems(items)
        }
    }

}