package com.vladimirkondenko.yoyocinema.domain

import io.reactivex.Completable
import io.reactivex.Scheduler

/**
 * Creates an {Observable} which executes a piece of logic in the background.
 */
abstract class UseCaseCompletable<PARAMS>(private val uiScheduler: Scheduler, private  val workerScheduler: Scheduler)
    : UseCase<PARAMS, Nothing?, Completable>() {

    abstract override fun build(params: PARAMS): Completable

    override fun execute(params: PARAMS, onComplete: (Nothing?) -> Unit, onError: (Throwable) -> Unit, onFinish: () -> Unit) {
        disposable = build(params)
                .subscribeOn(workerScheduler)
                .observeOn(uiScheduler)
                .doFinally(onFinish)
                .subscribe({ onComplete.invoke(null) }, onError)
    }

}