package com.example.actioninputspranto.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.actioninputspranto.BusSchedule
import com.example.actioninputspranto.daos.ScheduleDao

@Database(entities = [BusSchedule::class], version = 2)
abstract class ScheduleDB : RoomDatabase() {
    abstract fun getScheduleDao() : ScheduleDao
    //migration object-> extra column add hobe
    //for each version change we need one migration object



    companion object {
        private var db : ScheduleDB? = null

        private val migration_1_2 : Migration = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table 'tbl_schedule' add column 'image' text")

            }
        }

        fun getDB(context: Context):ScheduleDB{
            if (db == null){
                db = Room.databaseBuilder(
                    context.applicationContext,
                    ScheduleDB::class.java,
                "schedule_db"
                ).allowMainThreadQueries().addMigrations(migration_1_2).build()
                return db!!
            }
            return db!!
        }
    }
}