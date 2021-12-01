package com.softradix.nearbysearch.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

class AddHeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder()
        val token = "2ROaa2Rh9qu3WVTCms8FoVE4mSfHQHC7QJua95-kKT-PqzIlLSrs4tmHVdtdFw_66-JNfRiJmbCByHTvFNy5dQq-tpfS4FrPpupIzKlgELR3br-r5trpeFhrCRgwWnYx"
        builder.addHeader("Authorization", "Bearer $token")

        return chain.proceed(builder.build())
    }

    /**
     ******** get current timezone string *****************
     */
//    private fun getTimeZone(): String {
//        val tz = TimeZone.getDefault()
//        val offset = tz.rawOffset
//        return String.format("%s%02d:%02d", if (offset >= 0) "+" else "-", offset / 3600000, offset / 60000 % 60)
//    }

    /**
     ******** get current timezone string *****************
     */
//    private fun getTimeZoneString(): String {
//        return TimeZone.getDefault().id
//    }
}