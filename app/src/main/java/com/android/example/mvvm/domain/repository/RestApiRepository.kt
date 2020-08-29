package com.android.example.mvvm.domain.repository

import com.android.example.mvvm.api.Api
import com.android.example.mvvm.api.response.ColorsResponse
import com.android.example.mvvm.api.response.UsersResponse
import io.reactivex.Observable
import javax.inject.Inject
class RestApiRepository @Inject constructor(private val api: Api) {

    fun getUsers(): Observable<UsersResponse> {
        return api.fetchUsers()
    }
    fun getColors(): Observable<ColorsResponse> {
        return api.fetchColors()
    }

}