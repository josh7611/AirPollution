package com.zepplelin.airpollution.infrastructure.di

import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.zepplelin.airpollution.data.remote.AirConditionServer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private const val HTTP_TIMEOUT_IN_SEC = 30L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val builder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(2 * 60, TimeUnit.SECONDS)
            .connectTimeout(2 * 60, TimeUnit.SECONDS)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://data.epa.gov.tw")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideAirConditionApiServer(retrofit: Retrofit): AirConditionServer {
        return retrofit.create(AirConditionServer::class.java)
    }

    @Provides
    @Singleton
    fun provideApiKeyProvider(): ApiKeyProvider {
        return object :ApiKeyProvider {
            override val apiKey: String
                get() = "9be7b239-557b-4c10-9775-78cadfc555e9"
        }
    }
}
