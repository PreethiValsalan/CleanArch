package com.interapp.developer.domain.model

/**
Created by Preethi Valsalan on 2019-10-26
 */

enum class ResponseState {

    INITIAL_LOADING, SUCCESS, FAILED, LOADING, NETWORK_NOT_AVAILABLE;
}

sealed class Response(val responseState: ResponseState) {

    data class Failure<String>(val message : String, val state : ResponseState) : Response(state)
    data class Success<T>(val data : T, val state : ResponseState) : Response(state)
    data class Processing(val state : ResponseState) : Response(state)

    fun <String> failed(message: String) = Failure(message, ResponseState.FAILED)

    fun <T> success(data: T) = Success(data, ResponseState.SUCCESS)


    fun getIsFailed(): Boolean {
        return responseState === ResponseState.FAILED || responseState === ResponseState.NETWORK_NOT_AVAILABLE
    }

    fun getIsLoading(): Boolean {
        return responseState === ResponseState.LOADING
    }

}

