package com.example.api.retrofit


import android.util.Log
import com.eohi.haixinpad.api.retrofit.SSLContextSecurity
import com.example.base.ApiService
import com.example.utils.Preference
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    private var apiurl by Preference("ApiUrl", "http://122.51.182.66:3019/")

    companion object {
        fun getInstance() =
            SingletonHolder.INSTANCE

        private lateinit var retrofit: Retrofit
    }

    private object SingletonHolder {
        val INSTANCE = RetrofitClient()
    }


    init {
        retrofit = Retrofit.Builder()
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(apiurl)
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {

        // log拦截器  打印所有的log
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.i(
                "HttpLogging",
                message
            )
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .sslSocketFactory(SSLContextSecurity.createIgnoreVerifySSL("TLS"))
            .build()
    }

    fun create(): ApiService = retrofit.create(
        ApiService::class.java
    )

//    当有多个apiservice时
//    inline fun <reified  T> create():T{
//        return retrofit.create(T::class.java)
//    }


}