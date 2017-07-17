package lech.eyevideo.ui.adapter

import android.media.Image
import android.widget.ImageView
import com.bumptech.glide.Glide
import lech.eyevideo.R
import lech.eyevideo.mvp.model.domain.DiscoveryBean
import lech.library.base.adapter.BaseViewHolder
import lech.library.base.adapter.QuickAdapter

/**
 * Created by Android_61 on 2017/7/17.
 * Description
 * Others
 */
class DiscoveryAdapter(layoutResId:Int, data:MutableList<DiscoveryBean>):QuickAdapter<DiscoveryBean,BaseViewHolder>(layoutResId,data) {


    override fun convert(helper: BaseViewHolder, item: DiscoveryBean) {
        helper.setText(R.id.tv_title,item.name)
        val view = helper.getView<ImageView>(R.id.iv_photo)
        Glide.with(mContext).load(item.bgPicture).into(view)
    }
}