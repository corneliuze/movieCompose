package com.android.movieappcompose.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.Response

fun <T : Any> Single<Response<T>>.toResult(): Single<Result<T>> {
    return compose { item ->
        item.map {
            getAPIResult(it)
        }.onErrorReturn {
            Result.Error("-1", it.message ?: "Unable to Proceed bue to Network Failure ")
        }
    }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}

fun <T : Any> getAPIResult(response: Response<T>): Result<T> {
    if (response.isSuccessful) {
        val body = response.body()
        if (body != null) {
            return Result.Success(body)
        }
    } else {
        val errorBody = response.errorBody()
        if (errorBody != null) {
            val errorBodyString = errorBody.string()
            return Result.Error(
                getErrorCode(errorBodyString), getErrorMessage(errorBodyString)
            )
        }
    }
    // Fallback to regular status code and message
    return Result.Error("${response.code()}", response.message())
}

fun getErrorMessage(errorBody: String): String {
    return try {
        val errorBodyJsonObject = JSONObject(errorBody)
        errorBodyJsonObject.getString("message")
    } catch (e: Exception) {
        "Unable to Proceed bue to Network Failure $e"
    }
}

fun getErrorCode(errorBody: String): String {
    return try {
        val errorBodyJsonObject = JSONObject(errorBody)
        errorBodyJsonObject.getString("code")
    } catch (e: Exception) {
        "-1"
    }
}