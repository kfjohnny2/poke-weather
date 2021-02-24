package com.kfjohnny.pokweather.util.extensions

import android.annotation.SuppressLint

@SuppressLint("DefaultLocale")
fun String?.upperCaseFirstCharacter() : String {
    return if(this != null){
        "${this.substring(0,1).toUpperCase()}${this.substring(1).toLowerCase()}"
    } else{
        ""
    }
}