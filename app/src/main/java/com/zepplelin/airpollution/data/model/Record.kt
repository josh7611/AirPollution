package com.zepplelin.airpollution.data.model

import com.squareup.moshi.Json

data class Record(
    val AQI: String,
    val CO: String,
    val CO_8hr: String,
    val County: String,
    val ImportDate: String,
    val Latitude: String,
    val Longitude: String,
    val NO: String,
    val NO2: String,
    val NOx: String,
    val O3: String,
    val O3_8hr: String,
    val PM10: String,
    val PM10_AVG: String,
    @field:Json(name = "PM2.5") val PM25: String,
    @field:Json(name = "PM2.5_AV") val PM25_AVG: String,
    val Pollutant: String,
    val PublishTime: String,
    val SO2: String,
    val SO2_AVG: String,
    val SiteId: String,
    val SiteName: String,
    val Status: String,
    val WIND_DIREC: String,
    val WIND_SPEED: String
)