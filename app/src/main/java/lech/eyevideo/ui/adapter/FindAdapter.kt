package lech.eyevideo.ui.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import lech.eyevideo.R
import lech.eyevideo.mvp.model.domain.FindBean
import lech.library.base.adapter.BaseViewHolder
import lech.library.base.adapter.QuickAdapter

/**
 * Created by Android_61 on 2017/7/17.
 * Description
 * Others
 */
class FindAdapter(layoutResId:Int, data:MutableList<FindBean>):QuickAdapter<FindBean,BaseViewHolder>(layoutResId,data) {


    override fun convert(helper: BaseViewHolder, item: FindBean) {
        helper.setText(R.id.tv_title,item.name)
        val view = helper.getView<ImageView>(R.id.iv_photo)
        Glide.with(mContext).load(item.bgPicture).into(view)
    }
}