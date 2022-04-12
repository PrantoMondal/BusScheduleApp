package com.example.actioninputspranto.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.actioninputspranto.BusSchedule
import com.example.actioninputspranto.daos.ScheduleDao

@Database(entities = [BusSchedule::class], version = 1)
abstract class ScheduleDB : RoomDatabase() {
    abstract fun getScheduleDao() : ScheduleDao
    companion object {
        private var db : ScheduleDB? = null
        fun getDB(context: Context):ScheduleDB{
            if (db == null){
                db = Room.databaseBuilder(context.applicationContext,ScheduleDB::class.java,
                "schedule_db").allowMainThreadQueries().build()
                return db!!
            }
            return db!!
        }
    }
}