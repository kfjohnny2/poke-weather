package com.kfjohnny.pokweather.util.extensions

import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

fun View.getParentActivity(): AppCompatActivity?{
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

fun View.setBackgroundColorFromId( colorId : Int) {
    this.setBackgroundColor(ContextCompat.getColor(this.context, colorId))
}
fun Toolbar.setTitleTextColorFromId( colorId : Int) {
    this.setTitleTextColor(ContextCompat.getColor(this.context, colorId))
}