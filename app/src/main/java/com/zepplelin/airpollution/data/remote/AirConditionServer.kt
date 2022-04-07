package com.zepplelin.airpollution.data.remote

import com.zepplelin.airpollution.data.model.AirConditionResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AirConditionServer {

    @GET("/api/v1/aqx_p_432")
    fun getAirCondition(
        @Query("limit") limit: Int,
        @Query("api_key") apiKey: String,
        @Query("sort") sort: String,
        @Query("format") format: String,
    ): Single<AirConditionResult>
}