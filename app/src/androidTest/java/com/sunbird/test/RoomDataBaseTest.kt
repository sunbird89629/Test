package com.sunbird.test

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sunbird.test.room.MyDatabase
import com.sunbird.test.room.Student
import com.sunbird.test.room.StudentDao
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * 作者：王豪
 * 日期：2021/6/1
 * 描述：<必填>
 */

@RunWith(AndroidJUnit4::class)
class RoomDataBaseTest {

    lateinit var studentDao: StudentDao


    @Before
    fun initDatabase() {
        val db = MyDatabase.getInstance(ApplicationProvider.getApplicationContext())
        studentDao = db.getStudentDao()
    }

    @Test
    fun test_student_save() {
        studentDao.insert(Student("testname", 30))
    }

    @Test
    fun test_student_getAll() {
        val db = MyDatabase.getInstance(ApplicationProvider.getApplicationContext())
        val studentDao = db.getStudentDao()
        val result = studentDao.getAll()
        Assert.assertEquals(1, result.size)
    }

    @Test
    fun test_student_update() {
        val student = studentDao.getByPk(1)
        student.age = 40
        studentDao.update(student)
        Assert.assertEquals(40, studentDao.getByPk(1).age)
    }

}