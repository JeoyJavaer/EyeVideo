package lech.library.base

import android.app.Application
import lech.library.delegate.DelegateExtensions

/**
 * Created by Android_61 on 2017/7/13.
 * Description
 * Others
 */
abstract class BaseApp : Application() {
    companion object{
        var instance:BaseApp by DelegateExtensions.notNullSingleValue<BaseApp>()
    }
    override fun onCreate() {
        super.onCreate()
        instance=this
        doInit()
    }

    abstract fun doInit()


    override fun onTerminate() {
        super.onTerminate()
        doDestroy()
    }

    open fun doDestroy() {}


}