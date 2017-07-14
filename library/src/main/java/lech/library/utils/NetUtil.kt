package lech.library.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import lech.library.base.BaseApp

/**
 * Created by Android_61 on 2017/7/14.
 * Description  网络工具
 * Others
 */
object NetUtil {

    /**
     *检查网络是否可用
     */
    fun isNetConnected(): Boolean {
        return isNetConnected(BaseApp.instance)
    }


    private fun isNetConnected(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = manager.activeNetworkInfo
        return activeNetworkInfo != null
    }


    fun isNetworkConnected(context: Context, typeMobile: Int): Boolean{
        if(!isNetConnected(context)){
            return false
        }
        val connectManager  = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo : NetworkInfo? = connectManager.getNetworkInfo(typeMobile)
        if(networkInfo==null){
            return false
        }else{
            return  networkInfo.isConnected && networkInfo.isAvailable
        }
    }

    fun isPhoneNetConnected(context: Context): Boolean {
        val typeMobile = ConnectivityManager.TYPE_MOBILE
        return isNetworkConnected(context,typeMobile)
    }

    fun isWifiNetConnected(context: Context) : Boolean{
        val typeMobile = ConnectivityManager.TYPE_WIFI
        return  isNetworkConnected(context,typeMobile)
    }



}