package com.android.example.mvvm.di.modules

import DaggerFragment.ultil.helper.SharePreferenceHelper
import android.util.Log
import com.android.example.mvvm.App
import com.android.example.mvvm.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
@Module
class NetworkModule {

    private val CONNECTION_TIMEOUT: Int = 30000
    val sharedPreference = SharePreferenceHelper(App.sApplication!!)
    val token: String? = sharedPreference.getValueString(sharedPreference.ACCESSTOKEN)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        token.let { Log.d("Token:", token) }
        return httpClient
                .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                .addInterceptor{ chain ->
                    val newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " +token)
                            .build()
                    chain.proceed(newRequest)
                }
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

}