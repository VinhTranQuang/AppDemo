package com.android.example.mvvm.domain.model

import com.google.gson.annotations.SerializedName
data class Color (
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("year") val year: Int,
        @SerializedName("color") val color: String
)