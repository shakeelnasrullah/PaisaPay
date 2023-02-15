package com.pak.paisapay.base

sealed class BaseUIStates {
    object LoadingState : BaseUIStates()
    object ContentState : BaseUIStates()
    object EmptyState : BaseUIStates()
    class ErrorState(val message : String) : BaseUIStates()
}