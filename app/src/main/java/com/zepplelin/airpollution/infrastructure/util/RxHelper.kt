package com.zepplelin.airpollution.infrastructure.util

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RxHelper {
    companion object {
        fun <T> subscribeObservableOnIoObserveOnMain(): ObservableTransformer<T, T> {
            return ObservableTransformer { observable ->
                observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }

        fun <T> subscribeFlowableOnIoObserveOnMain(): FlowableTransformer<T, T> {
            return FlowableTransformer { flowable ->
                flowable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }

        fun <T> subscribeSingleOnIoObserveOnMain(): SingleTransformer<T, T> {
            return SingleTransformer { observable ->
                observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }

        fun subscribeCompletableOnIoObserveOnMain(): CompletableTransformer {
            return CompletableTransformer { observable ->
                observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}

fun <T> Observable<T>.delayEach(interval: Long, timeUnit: TimeUnit): Observable<T> =
    Observable.zip(
        this,
        Observable.interval(interval, timeUnit),
        BiFunction { item, _ -> item }
    )