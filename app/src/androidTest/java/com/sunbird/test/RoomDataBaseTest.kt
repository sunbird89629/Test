package com.sunbird.test

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sunbird.test.room.MyDatabase
import com.sunbird.test.room.Student
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * 作者：王豪
 * 日期：2021/6/1
 * 描述：<必填>
 */

@RunWith(AndroidJUnit4::class)
class RoomDataBaseTest {

    @Test
    fun test_student_save() {
        val db = MyDatabase.getInstance(ApplicationProvider.getApplicationContext())
        val studentDao = db.getStudentDao()
        studentDao.insert(Student("testname", 30))
    }

    @Test
    fun test_student_getAll() {
        val db = MyDatabase.getInstance(ApplicationProvider.getApplicationContext())
        val studentDao = db.getStudentDao()
        val result = studentDao.getAll()
        Assert.assertEquals(1, result.size)
    }

}