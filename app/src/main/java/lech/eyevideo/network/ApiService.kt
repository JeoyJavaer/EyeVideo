package lech.eyevideo.network

import io.reactivex.Observable
import lech.eyevideo.mvp.model.domain.FindBean
import lech.eyevideo.mvp.model.domain.HomeBean
import lech.eyevideo.mvp.model.domain.HotBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Android_61 on 2017/7/14.
 * Description  网络请求
 * Others
 */
interface ApiService {
    companion object{
        val BASE_URL :String get() = "http://baobab.kaiyanapp.com/api/"
    }

    //获取首页第一页数据
    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getHomeListData():Observable<HomeBean>

    //获取首页第一页之后的数据  ?date=1499043600000&num=2
    @GET("v2/feed")
    fun getHomeMoreData(@Query("date") date :String, @Query("num") num :String) : Observable<HomeBean>

    //获取发现频道信息
    @GET("v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getDiscoveryData() : Observable<MutableList<FindBean>>

    //获取发现频道详情信息
    @GET("v3/videos")
    fun getFindDetailData(@Query("categoryName") categoryName :String,@Query("strategy") strategy :String,
                          @Query("udid") udid :String,@Query("vc") vc :Int) : Observable<HotBean>

    //获取发现详情加载更多消息
    @GET("v3/videos")
    fun getFindDetailMoreData(@Query("start") start :Int,@Query("num") num :Int,
                              @Query("categoryName") categoryName :String,@Query("strategy") strategy :String) : Observable<HotBean>
    //获取关键词搜索相关信息
    @GET("v1/search")
    fun getSearchData(@Query("num") num :Int,@Query("query") query :String,
                      @Query("start") start :Int) : Observable<HotBean>

    //获取热门排行信息
    @GET("v3/ranklist")
    fun getHotData(@Query("num") num :Int,@Query("strategy") strategy :String,
                   @Query("udid") udid :String,@Query("vc") vc :Int) : Observable<HotBean>

}