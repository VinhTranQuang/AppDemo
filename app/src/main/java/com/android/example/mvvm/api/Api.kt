package com.android.example.mvvm.api

import com.android.example.mvvm.api.response.ColorsResponse
import com.android.example.mvvm.api.response.UsersResponse
import io.reactivex.Observable
import retrofit2.http.GET
interface Api {

    @GET("users")
    fun fetchUsers(): Observable<UsersResponse>

    @GET("colors")
    fun fetchColors(): Observable<ColorsResponse>

}