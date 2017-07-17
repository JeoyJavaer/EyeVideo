package lech.eyevideo.ui.adapter

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import lech.eyevideo.R
import lech.eyevideo.mvp.model.domain.ItemListBean
import lech.library.base.adapter.BaseViewHolder
import lech.library.base.adapter.QuickAdapter

/**
 * Created by Android_61 on 2017/7/14.
 * Description
 * Others
 */

class HomeAdapter(layoutResId: Int, data: List<ItemListBean>) : QuickAdapter<ItemListBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: ItemListBean) {
        helper.setText(R.id.tv_title,item.data!!.title)
        helper.setText(R.id.tv_detail,item.data.category)
        val view = helper.getView<ImageView>(R.id.iv_photo)
        val user = helper.getView<ImageView>(R.id.iv_user)


        Glide.with(mContext).load(item.data.cover!!.feed).into(view)
        val author = item.data.author
        if (author != null && !TextUtils.isEmpty(author.icon)) {
            user.visibility = View.VISIBLE
            Glide.with(mContext).load(item.data.author!!.icon).into(user)
        } else {
            user.visibility = View.GONE
        }

    }
}
