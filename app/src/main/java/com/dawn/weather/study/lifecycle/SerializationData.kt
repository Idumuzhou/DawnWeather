package com.dawn.weather.study.lifecycle

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

/**
 *  @author: LXL
 *  @description: 序列化数据 Serializable  Parcelable
 *  @date: 2021/11/22 16:30
 */


/**
 * Serializable
 */
class PersonS : Serializable {
    var name = ""
    var age = 0
}


/**
 * Parcelable
 */
class PersonP : Parcelable {
    var name = ""
    var age = 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name) // 写出name
        parcel.writeInt(age) // 写出age
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonP> {
        override fun createFromParcel(parcel: Parcel): PersonP {
            val person = PersonP()
            person.name = parcel.readString() ?: "" // 读取name
            person.age = parcel.readInt() // 读取age
            return person
        }

        override fun newArray(size: Int): Array<PersonP?> {
            return arrayOfNulls(size)
        }
    }

}

/**
 * Parcelable Kotlin 简化写法
 * 添加插件 apply plugin: 'kotlin-parcelize'
 */
@Parcelize
class PersonJ(var name: String, var age: Int) :  Parcelable

