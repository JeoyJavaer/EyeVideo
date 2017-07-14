package lech.library.extension

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Android_61 on 2017/7/14.
 * Description
 * Others
 */
fun <T> Observable<T>.applySchedulers():Observable<T>{
    return  subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}