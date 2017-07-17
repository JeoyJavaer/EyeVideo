package lech.eyevideo.mvp.model.domain

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import javax.xml.transform.Source

/**
 * Created by Android_61 on 2017/7/17.
 * Description
 * Others
 */
data  class VideoBean(val  feed:String?,val title:String,val description:String,
    val duration:Long?,val playUrl:String,val category:String,val blurred:String,
     val collect:Int ?,val share:Int ?, val reply:Int?,val time:Long):Parcelable,Serializable{

   companion object{
       val CREATOR:Parcelable.Creator<VideoBean> = object:Parcelable.Creator<VideoBean>{
           override fun createFromParcel(source: Parcel): VideoBean = VideoBean(source)

           override fun newArray(size: Int): Array<VideoBean?> = arrayOfNulls(size)

       }


   }


    constructor(source: Parcel):this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readValue(Long::class.java.classLoader) as Long?,
            source.readString(),
            source.readString(),
            source.readString(),
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readValue(Int::class.java.classLoader) as Int?,

            source.readLong()

    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(feed)
        dest.writeString(title)
        dest.writeString(description)
        dest.writeValue(duration)
        dest.writeString(playUrl)
        dest.writeString(category)
        dest.writeString(blurred)
        dest.writeValue(collect)
        dest.writeValue(share)
        dest.writeValue(reply)
        dest.writeLong(time)
    }

    override fun describeContents(): Int=0

}
