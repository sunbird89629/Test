package com.sunbird.test.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 作者：王豪
 * 日期：2021/6/1
 * 描述：<必填>
 */

@Entity(tableName = "student")
data class Student(
    var name: String,
    var age: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}