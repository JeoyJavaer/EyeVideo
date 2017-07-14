package lech.eyevideo.mvp.model

import android.content.Context
import io.reactivex.Observable
import lech.eyevideo.mvp.model.domain.HomeBean
import lech.eyevideo.network.ApiService
import lech.eyevideo.network.RetrofitClient

/**
 * Created by Android_61 on 2017/7/13.
 * Description
 * Others
 */
class HomeModel {

    fun loadData(context: Context, isFirst: Boolean, date: String): Observable<HomeBean> {
        val instance = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService = instance.create(ApiService::class.java)
        when (isFirst) {
            true -> return apiService?.getHomeListData()!!

            false -> return apiService?.getHomeMoreData(date, "2")!!
        }
    }
}