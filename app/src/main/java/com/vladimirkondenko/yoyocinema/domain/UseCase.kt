package com.vladimirkondenko.yoyocinema.domain

import io.reactivex.disposables.Disposable

abstract class UseCase<PARAMS, RESULT, CONTEXT> : Disposable {

    protected var disposable: Disposable? = null

    abstract fun build(params: PARAMS): CONTEXT

    abstract fun execute(params: PARAMS, onSuccess: (RESULT) -> Unit = {}, onError: (Throwable) -> Unit = {}, onFinish: () -> Unit = {})

    override fun isDisposed() = disposable?.isDisposed?:true

    override fun dispose() {
        disposable?.dispose()
    }

}