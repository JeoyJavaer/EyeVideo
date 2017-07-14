package lech.eyevideo.mvp.model.domain

/**
 * Created by Android_61 on 2017/7/13.
 * Description
 * Others
 */

data class HomeBean(val nextPageUrl: String, val nextPublishTime: String, val newestIssueType: String,val dialog: Any?, val issueList: MutableList<IssueListBean>)

data class IssueListBean(val releaseTime: Long,val type: String?,
                         val date: Long,val publishTime: Long,val count: Int,
                         val itemList: List<ItemListBean>?)

data class ItemListBean(val type: String?,val data: DataBean?,val tag: Any?)

data class DataBean(val dataType: String?,val id: Int,val title: String?,
                    val description:String?, val image: String?,val actionUrl: String?,
                    val adTrack: Any?,val isShade: Boolean,
                    val label: Any?,val labelList: Any?,val header: Any?, val category: String?,
                    val duration: Long?,val playUrl: String,val cover: CoverBean?,val author: AuthorBean?,
                    val releaseTime : Long?,val consumption: ConsumptionBean?)
data class CoverBean(val feed : String?,val detail : String?,
                     val blurred : String?,val sharing : String?,val homepage:String?)

data class ConsumptionBean(val collectionCount: Int,val shareCount: Int, val replyCount: Int)

data class AuthorBean(val icon: String)