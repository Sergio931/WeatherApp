package com.sergio931.weatherapp.utils

import kotlin.math.abs

fun <A, B> Pair<A, B>.compare(value: Pair<A, B>): Boolean {
    return this.first == value.first && this.second == value.second
}

fun String.addTempPrefix() = when {
    toInt() > 0 -> "+$this"
    abs(toInt()) == 0 -> "${this.toInt()}"
    else -> this
}