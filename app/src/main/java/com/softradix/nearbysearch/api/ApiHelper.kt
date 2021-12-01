package com.softradix.nearbysearch.api

import com.softradix.nearbysearch.data.Status
import com.softradix.nearbysearch.utils.Constants
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by android on 3/11/17.
 * *
 */
object ApiHelper {

    private var mRetrofit: Retrofit

    // Creating Retrofit Object
    init {
        mRetrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
    }

    // Creating OkHttpclient Object
    private fun getClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val interceptor =
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        return OkHttpClient().newBuilder().connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .addNetworkInterceptor(AddHeaderInterceptor())
            .build()
    }

    //Creating service class for calling the web services
    fun createService(): WebService {
        return mRetrofit.create(WebService::class.java)
    }

    // Handling error messages returned by Apis
    fun handleApiError(body: ResponseBody): String {
        var errorMsg: String?
        try {
            val errorConverter: Converter<ResponseBody, Status?> =
                mRetrofit.responseBodyConverter(Status::class.java, arrayOfNulls(0))
            val error: Status? = errorConverter.convert(body)
            error?.message.let { msg ->
                errorMsg = msg
            }
        } catch (e: Exception) {
            errorMsg = Constants.SOMETHING_WENT_WRONG
        }
        return errorMsg ?: Constants.SOMETHING_WENT_WRONG
    }

    fun handleApiErrorStatus(body: ResponseBody): Status {
        var errorMsg: Status? = try {
            val errorConverter: Converter<ResponseBody, Status?> =
                mRetrofit.responseBodyConverter(Status::class.java, arrayOfNulls(0))
            errorConverter.convert(body)
        } catch (e: Exception) {
            Status()
        }
        return errorMsg ?: Status()
    }

}
