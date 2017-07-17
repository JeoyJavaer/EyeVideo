package lech.eyevideo.mvp.model

import android.content.Context
import io.reactivex.Observable
import lech.eyevideo.mvp.model.domain.HotBean
import lech.eyevideo.network.ApiService
import lech.eyevideo.network.RetrofitClient

/**
 * Created by Android_61 on 2017/7/17.
 * Description
 * Others
 */
class HotModel {

    fun loadData(context:Context,category:String):Observable<HotBean>?{
       return RetrofitClient.getInstance(context,ApiService.BASE_URL).create(ApiService::class.java)
                ?.getHotData(10,category,"26868b32e808498db32fd51fb422d00175e179df",83)
    }

}