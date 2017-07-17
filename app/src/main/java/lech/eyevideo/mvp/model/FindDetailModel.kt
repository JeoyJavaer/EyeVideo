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
class FindDetailModel {

    fun loadData(context: Context, categoryName: String, strategy: String?): Observable<HotBean>? {
        return RetrofitClient.getInstance(context, ApiService.BASE_URL)
                .create(ApiService::class.java)
                ?.getFindDetailData(categoryName, strategy!!, "26868b32e808498db32fd51fb422d00175e179df", 83)
    }


    fun loadMoreData(context: Context, start: Int, categoryName: String, strategy: String?): Observable<HotBean>? {
        return RetrofitClient.getInstance(context, ApiService.BASE_URL)
                .create(ApiService::class.java)
                ?.getFindDetailMoreData(start, 10, categoryName, strategy!!)
    }
}