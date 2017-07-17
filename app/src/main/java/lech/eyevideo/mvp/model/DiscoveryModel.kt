package lech.eyevideo.mvp.model

import android.content.Context
import io.reactivex.Observable
import lech.eyevideo.mvp.model.domain.DiscoveryBean
import lech.eyevideo.network.ApiService
import lech.eyevideo.network.RetrofitClient

/**
 * Created by Android_61 on 2017/7/17.
 * Description
 * Others
 */
class DiscoveryModel() {

    fun loadData(context: Context): Observable<MutableList<DiscoveryBean>> {
      return  RetrofitClient.getInstance(context,ApiService.BASE_URL).create(ApiService::class.java)!!.getDiscoveryData()
    }

}