package com.example.lab3.models

sealed class LoadingResult<out T> {
    data class Success<out R>(val value: R): LoadingResult<R>()
    data class Failure(
        val message: String?,
        val moreInfo: String?
    ): LoadingResult<Nothing>()
}