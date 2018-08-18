package com.vladimirkondenko.yoyocinema.domain

import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Creates an {Observable} which executes a piece of logic in the background.
 */
abstract class UseCaseSingle<PARAMS, RESULT>(private val uiScheduler: Scheduler, private  val workerScheduler: Scheduler)
    : UseCase<PARAMS, RESULT, Single<RESULT>>() {

    abstract override fun build(params: PARAMS): Single<RESULT>

    override fun execute(params: PARAMS, onSuccess: (RESULT) -> Unit, onError: (Throwable) -> Unit, onFinish: () -> Unit) {
        disposable = build(params)
                .subscribeOn(workerScheduler)
                .observeOn(uiScheduler)
                .doOnError(Throwable::printStackTrace)
                .doFinally(onFinish)
                .subscribe(onSuccess, onError)
    }

}