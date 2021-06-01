package com.sunbird.test.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * 作者：王豪
 * 日期：2021/6/1
 * 描述：<必填>
 */
@Dao
interface StudentDao {
    @Insert
    fun insert(student: Student)

    @Delete
    fun delete(student: Student)

    @Delete
    fun update(student: Student)

    @Query("SELECT * FROM student")
    fun getAll(): List<Student>

    @Query("SELECT * FROM student where id=:id")
    fun getByPk(id: Int): Student
}