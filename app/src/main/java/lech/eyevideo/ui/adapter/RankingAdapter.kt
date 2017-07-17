package lech.eyevideo.ui.adapter

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import lech.eyevideo.R
import lech.eyevideo.mvp.model.domain.HotBean
import lech.library.base.adapter.BaseViewHolder
import lech.library.base.adapter.QuickAdapter

/**
 * Created by Android_61 on 2017/7/17.
 * Description
 * Others
 */
class RankingAdapter(layoutResId: Int, data: List<HotBean.ItemListBean.DataBean>) : QuickAdapter<HotBean.ItemListBean.DataBean, BaseViewHolder>(layoutResId,data) {

    override fun convert(helper: BaseViewHolder?, item: HotBean.ItemListBean.DataBean?) {
        Log.i("tag",item.toString())
        val category = item?.category
        val duration = item?.duration
        val minute =duration?.div(60)
        val second = duration?.minus((minute?.times(60)) as Long )
        val realMinute : String
        val realSecond : String
        if(minute!!<10){
            realMinute = "0"+minute
        }else{
            realMinute = minute.toString()
        }
        if(second!!<10){
            realSecond = "0"+second
        }else{
            realSecond = second.toString()
        }
        val subTitle="$category / $realMinute'$realSecond''"


        helper?.setText(R.id.tv_title, item.title)
        helper?.setText(R.id.tv_time, subTitle)
        val photo = helper?.getView<ImageView>(R.id.iv_photo)
        Glide.with(mContext).load(item.cover?.feed).into(photo)
    }


}