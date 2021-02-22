package com.kfjohnny.pokweather.util.extensions

fun String.upperCaseFirstCharacter() : String {
    return "${this.substring(0,1).toUpperCase()}${this.substring(1).toLowerCase()}"
}