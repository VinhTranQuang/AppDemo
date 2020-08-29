package com.android.example.mvvm.domain.model

import com.google.gson.annotations.SerializedName
data class User (
        @SerializedName("id") val id: Int,
        @SerializedName("first_name") val firstName: String,
        @SerializedName("last_name") val lastName: String,
        @SerializedName("avatar") val avatar: String?
)