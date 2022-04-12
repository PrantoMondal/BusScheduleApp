package com.example.actioninputspranto.daos

import androidx.room.*
import com.example.actioninputspranto.BusSchedule

@Dao
interface ScheduleDao {
    @Insert
    fun addSchedule(busSchedule: BusSchedule)

    @Update
    fun updateSchedule(busSchedule: BusSchedule)

    @Delete
    fun deleteSchedule(busSchedule: BusSchedule)

    @Query("select * from tbl_schedule")
    fun getAllSchedules():List<BusSchedule>
}