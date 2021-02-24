package com.kfjohnny.pokweather.util

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.model.pokemon.Pokemon

fun changeDynamicBackgroundColor(it: Pokemon, activity: Activity) {
    changeDynamicBackgroundColor(it, activity, null)
}

fun changeDynamicBackgroundColor(it: Pokemon, activity: Activity, secondaryView: View?) {
    Glide.with(activity).asBitmap().load(it.sprites.frontDefault)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                // Pick one of the swatches
                val palette = Palette.from(resource).generate()
                val vibrantLightDominant: Palette.Swatch? = palette.lightVibrantSwatch
                if (vibrantLightDominant != null) {
                    // Set the background color of a layout based on the vibrant color
                    val toolbar = activity.findViewById<Toolbar>(R.id.tbMain)
                    toolbar?.setBackgroundColor(vibrantLightDominant.rgb)
                    toolbar?.setTitleTextColor(vibrantLightDominant.titleTextColor)
                }

                if (secondaryView != null) {
                    val vibrantDominant: Palette.Swatch? = palette.dominantSwatch
                    if (vibrantDominant != null) {
                        secondaryView.setBackgroundColor(vibrantDominant.rgb)
                    }
                }
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }

        })
}