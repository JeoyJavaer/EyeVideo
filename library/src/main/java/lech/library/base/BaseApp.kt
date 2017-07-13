package lech.library.base

import android.app.Application

/**
 * Created by Android_61 on 2017/7/13.
 * Description
 * Others
 */
abstract class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        doInit()
    }

    abstract fun doInit()


    override fun onTerminate() {
        super.onTerminate()
        doDestroy()
    }

    open fun doDestroy() {}


}