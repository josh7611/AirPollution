package com.zepplelin.airpollution.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.util.Log.e
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zepplelin.airpollution.data.model.AirConditionResult
import com.zepplelin.airpollution.data.remote.AirConditionServer
import com.zepplelin.airpollution.infrastructure.di.ApiKeyProvider
import com.zepplelin.airpollution.infrastructure.util.RxHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.logging.Logger
import javax.inject.Inject

private const val TAG = "SearchViewModel"
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val airConditionServer: AirConditionServer,
    private val apiKeyProvider: ApiKeyProvider
) : ViewModel() {
    private val _airConditionResult = MutableLiveData<AirConditionResult>()
    val airConditionResult: LiveData<AirConditionResult>
        get() = _airConditionResult


    @SuppressLint("CheckResult")
    fun fetch(searchText: String) {
        airConditionServer.getAirCondition(1000, apiKeyProvider.apiKey, "ImportDate desc", "json")
            .compose(RxHelper.subscribeSingleOnIoObserveOnMain())
            .subscribeBy (
                onError = {
                    Log.e(TAG, ", ${Log.getStackTraceString(it)}")
                },
                onSuccess = {
                    Log.d(TAG, it.toString())
                    _airConditionResult.value = it
                })
    }
}

