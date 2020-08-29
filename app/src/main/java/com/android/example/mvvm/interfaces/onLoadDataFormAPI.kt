package com.android.example.mvvm.interfaces


interface onLoadDataFormAPI {
    fun onShowLoading()
    fun onFailure(t: Throwable)
    fun onResponse(response: Any?)
}