package com.zepplelin.airpollution.data.model

data class AirConditionResult(
    val __extras: Extras,
    val _links: Links,
    val distribution: List<Distribution>,
    val fields: List<Field>,
    val include_total: Boolean,
    val limit: Int,
    val offset: Int,
    val records: List<Record>,
    val records_format: String,
    val resource_id: String,
    val sort: String,
    val total: Int
)