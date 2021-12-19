package com.zyp.codetest.api

import com.zyp.codetest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        private const val BASE_URL: String = BuildConfig.API_URL;

        var retrofitService: ApiService? = null

        fun getInstance(): ApiService {

            if (retrofitService == null) {


                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)

                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()

                retrofitService = retrofit.create(ApiService::class.java)
            }

            return retrofitService!!
        }


    }
}