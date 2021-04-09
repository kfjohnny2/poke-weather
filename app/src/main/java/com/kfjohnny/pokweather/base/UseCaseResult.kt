package com.kfjohnny.pokweather.base

const val EMPTY_DEFAULT_MESSAGE = "No results found"
sealed class UseCaseResult<out T : Any>{
    class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    class Error(val exception: Throwable) : UseCaseResult<Nothing>()
    class Empty(val emptyMessage: String = EMPTY_DEFAULT_MESSAGE) : UseCaseResult<Nothing>()
}