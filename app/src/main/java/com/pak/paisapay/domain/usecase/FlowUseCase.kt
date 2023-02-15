package com.pak.paisapay.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in Param, Result>(private val coroutineDispatcher: CoroutineDispatcher) {
    operator fun invoke(parameters: Param): Flow<Result> = execute(parameters)
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameters: Param): Flow<Result>
}