package com.example.actioninputspranto.repos

import androidx.lifecycle.LiveData
import com.example.actioninputspranto.BusSchedule
import com.example.actioninputspranto.daos.ScheduleDao

class ScheduleLocalRepository (private val dao:ScheduleDao){
    fun addSchedule(busSchedule: BusSchedule) = dao.addSchedule(busSchedule)

    fun updateSchedule(busSchedule: BusSchedule) = dao.updateSchedule(busSchedule)

    fun deleteSchedule(busSchedule: BusSchedule) = dao.deleteSchedule(busSchedule)

    fun getAllSchedules(): LiveData<List<BusSchedule>> = dao.getAllSchedule()
}