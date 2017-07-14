package lech.eyevideo.network

import android.content.Context
import android.util.Log
import lech.library.utils.NetUtil
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Android_61 on 2017/7/14.
 * Description
 * Others
 */
class CacheInterceptor(context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        var request = chain?.request()
        if (NetUtil.isNetConnected()) {
            val response = chain?.proceed(request)
            val maxAge = 60
            val cacheControl = request?.cacheControl().toString()
            Log.e("CacheInterceptor", "load cache:" + cacheControl)
            return response?.newBuilder()?.removeHeader("Pragma")?.removeHeader("Cache-Control")?.header("Cache-Control", "public, max-age=" + maxAge)?.build()!!
        } else {

            Log.e("CacheInterceptor", "no cache:")
            request = request?.newBuilder()?.cacheControl(CacheControl.FORCE_CACHE)?.build()
            val response = chain?.proceed(request)
            val maxStale=60*60*24
            return response?.newBuilder()?.removeHeader("Pragma")?.removeHeader("Cache-Control")?.header("Cache-Control", "public, max-age=" + maxStale)?.build()!!
        }


    }
}