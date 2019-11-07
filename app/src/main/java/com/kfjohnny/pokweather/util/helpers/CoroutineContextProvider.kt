package com.kfjohnny.pokweather.util.helpers

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class CoroutineContextProvider() {
    open val Main: CoroutineContext = Dispatchers.Unconfined
    open val IO: CoroutineContext = Dispatchers.Unconfined
}
