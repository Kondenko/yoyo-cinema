package com.vladimirkondenko.yoyocinema.domain

import io.reactivex.Observable
import io.reactivex.Scheduler

/**
 * Creates an {Observable} which executes a piece of logic in the background.
 */
abstract class UseCaseObservable<PARAMS, RESULT>(private val uiScheduler: Scheduler, private  val workerScheduler: Scheduler)
    : UseCase<PARAMS, RESULT, Observable<RESULT>>() {

    abstract override fun build(params: PARAMS): Observable<RESULT>

    override fun execute(params: PARAMS, onNext: (RESULT) -> Unit, onError: (Throwable) -> Unit, onFinish: () -> Unit) {
        disposable = build(params)
                .subscribeOn(workerScheduler)
                .observeOn(uiScheduler)
                .doFinally(onFinish)
                .subscribe(onNext, onError)
    }

}