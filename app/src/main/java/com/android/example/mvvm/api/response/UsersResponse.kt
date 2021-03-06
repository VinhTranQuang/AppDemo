package com.android.example.mvvm.api.response

import com.android.example.mvvm.domain.model.User
import com.google.gson.annotations.SerializedName
data class UsersResponse (
        @SerializedName("page") val page: Int,
        @SerializedName("per_page") val perPage: Int,
        @SerializedName("total") val total: Int,
        @SerializedName("total_pages") val totalPages: Int,
        @SerializedName("data") val data: List<User>
)