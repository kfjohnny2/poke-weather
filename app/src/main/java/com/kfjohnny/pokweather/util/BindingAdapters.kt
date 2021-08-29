package com.kfjohnny.pokweather.util

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.databinding.BindingAdapter
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.util.extensions.getParentActivity
import com.kfjohnny.pokweather.util.extensions.onSearchTextChanged
import com.kfjohnny.pokweather.util.extensions.setBackgroundColorFromId
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
 * Binding function: Add textwatcher funcion on textChanged extension method
 *
 * @param view  EditText to add extension function
 * @param func  Function to run on textwatcher callback
 */
@BindingAdapter("addOnTextChanged")
fun setOnTextChanged(view: EditText, func: Runnable) {
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
        Glide.with(view.context).load(text).diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(R.drawable.img_pokemon_placeholder).into(view)
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

/**
 * @author Created by Johnnylee Rocha (kfjohnny2) on 5/5/2021.
 */
/**
 * Binding adapters for rounding view Borders Shape without the need of creating a new drawable res
 * You can pass individual borders corner radius for each side of the view and define a backgroundColor
 * Example: on the xml view, you can call it like that:
 *                                                      app:cornerBottomRight="@{20}"
 *                                                      app:cornerBottomLeft="@{20}"
 *                                                      app:cornerTopLeft="@{20}"
 *                                                      app:cornerTopRight="@{20}"
 *                                                      app:backgroundRoundedColor="@{@color/white}"
 * @param cornerBottomLeft Bottom Left Corner value
 * @param cornerBottomRight Bottom Right Corner value
 * @param cornerTopLeft Top Left Corner value
 * @param cornerTopRight Top Right Corner value
 * @param backgroundRoundedColor The background resource color
 */
@SuppressLint("ResourceType")
@BindingAdapter(
    value = ["corners", "cornerTopRight", "cornerTopLeft", "cornerBottomRight", "cornerBottomLeft", "backgroundRoundedColor"],
    requireAll = false
)
fun View.setRoundedBackground(
    corners: Float = 0f,
    cornerTopRight: Float = 0f, cornerTopLeft: Float = 0f,
    cornerBottomRight: Float = 0f, cornerBottomLeft: Float = 0f,
    backgroundRoundedColor: Int = ContextCompat.getColor(context, R.color.white)) {


    val topRight = (if (cornerTopRight <= 0) corners else cornerTopRight).convertToDP(resources)
    val topLeft = (if (cornerTopLeft <= 0) corners else cornerTopLeft).convertToDP(resources)
    val bottomRight = (if (cornerBottomRight <= 0) corners else cornerBottomRight).convertToDP(resources)
    val bottomLeft = (if (cornerBottomLeft <= 0) corners else cornerBottomLeft).convertToDP(resources)

    val shape = ShapeDrawable(
        RoundRectShape(
            floatArrayOf(
                topLeft, topLeft, topRight, topRight, bottomRight, bottomRight, bottomLeft, bottomLeft), null, null)
    )
    shape.paint.color = backgroundRoundedColor
    this.background = shape
}