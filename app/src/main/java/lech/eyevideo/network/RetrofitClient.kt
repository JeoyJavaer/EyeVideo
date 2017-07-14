package lech.eyevideo.network

import android.content.Context
import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit

/**
 * Created by Android_61 on 2017/7/14.
 * Description  网络请求工具
 * Others
 */
class RetrofitClient private constructor(context: Context, baseUrl: String) {


    companion object{

        var instance:RetrofitClient?=null
        fun getInstance(context: Context,baseUrl: String):RetrofitClient{
            if (instance == null) {
                synchronized(RetrofitClient::class) {
                    if (instance == null) {
                        instance= RetrofitClient(context,baseUrl)
                    }
                }
            }

            return  instance!!
        }

    }

    var httpCacheDirectory: File? = null
    val mContext: Context = context
    var cache: Cache? = null
    var okHttpClient: OkHttpClient? = null
    var retrofit: Retrofit? = null
    val DEFAULT_TIMEOUT: Long = 10
    val DEFAULT_CACHE_LENGTH: Long = 1024 * 1024 * 10
    val url = baseUrl

    init {
        if (httpCacheDirectory == null) {
            httpCacheDirectory = File(mContext.cacheDir, context.packageName)
        }

        try {
            if (cache == null) {
                cache = Cache(httpCacheDirectory, DEFAULT_CACHE_LENGTH)

            }
        } catch(e: Exception) {
            Log.e("net", "Cache Error")
        }

        okHttpClient=OkHttpClient.Builder()
                .addNetworkInterceptor (HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(cache)
                .addInterceptor(CacheInterceptor(context))
                .addNetworkInterceptor (CacheInterceptor(context))
                .connectTimeout(DEFAULT_TIMEOUT,TimeUnit.MINUTES)
                .writeTimeout(DEFAULT_TIMEOUT,TimeUnit.MINUTES)
                .build()

        retrofit=Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build()
    }

    fun <T>create(service:Class<T>?):T?{
        if (service == null) {
            throw RuntimeException("Api Service is null")
        }
        return  retrofit?.create(service)
    }




}