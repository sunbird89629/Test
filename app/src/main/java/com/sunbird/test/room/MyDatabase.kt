package com.sunbird.test.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * 作者：王豪
 * 日期：2021/6/1
 * 描述：<必填>
 */

@Database(
    version = 1,
    entities = [
        Student::class
    ]
)
abstract class MyDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "my_db"
        private var INSTANCE: MyDatabase? = null
        fun getInstance(context: Context): MyDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MyDatabase::class.java,
                            DB_NAME
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun getStudentDao(): StudentDao
}