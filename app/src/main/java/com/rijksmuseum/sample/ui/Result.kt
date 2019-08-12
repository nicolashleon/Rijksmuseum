package com.rijksmuseum.sample.ui

class Result<T> {
    var value: T? = null
    var error: Throwable? = null

    constructor(value: T) {
        this.value = value
    }

    constructor(error: Throwable) {
        this.error = error
    }
}