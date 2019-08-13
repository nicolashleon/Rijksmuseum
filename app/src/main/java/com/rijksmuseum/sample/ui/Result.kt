package com.rijksmuseum.sample.ui

/**
 * Adapted from
 * https://developer.android.com/jetpack/docs/guide
 */
sealed class Result<T>(val data: T? = null) {
    class Success<T>(data: T) : Result<T>(data)
    class Loading<T>(data: T? = null) : Result<T>(data)
    class Error<T>(message: Int? = null, throwable: Throwable?, data: T? = null) : Result<T>(data)
}