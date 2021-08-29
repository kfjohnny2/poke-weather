package com.kfjohnny.pokweather.util

import android.content.res.Resources
import android.util.TypedValue

fun Float.convertToDP(resources: Resources): Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, resources.displayMetrics)