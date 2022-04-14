package com.example.actioninputspranto.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.actioninputspranto.BusSchedule
import com.example.actioninputspranto.db.ScheduleDB
import com.example.actioninputspranto.repos.ScheduleLocalRepository

class ScheduleViewModel (application: Application)
    :AndroidViewModel(application){
        private lateinit var repository: ScheduleLocalRepository
        init {
            val dao = ScheduleDB.getDB(application).getScheduleDao()
            repository = ScheduleLocalRepository(dao)
        }
    fun addSchedule(busSchedule: BusSchedule) = repository.addSchedule(busSchedule)

    fun updateSchedule(busSchedule: BusSchedule) = repository.updateSchedule(busSchedule)

    fun deleteSchedule(busSchedule: BusSchedule) = repository.deleteSchedule(busSchedule)

    fun getAllSchedules(): LiveData<List<BusSchedule>> = repository.getAllSchedules()

}