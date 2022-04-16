package com.example.actioninputspranto.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.actioninputspranto.BusSchedule
import com.example.actioninputspranto.db.ScheduleDB

import com.example.actioninputspranto.repos.ScheduleLocalRepository
import kotlinx.coroutines.launch

class ScheduleViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var repository: ScheduleLocalRepository
    init {
        val dao = ScheduleDB.getDB(application).getScheduleDao()
        repository = ScheduleLocalRepository(dao)
    }
    fun addSchedule(busSchedule: BusSchedule){
        viewModelScope.launch {
            repository.addSchedule(busSchedule)
        }

    }

    fun deleteSchedule(busSchedule: BusSchedule){
        viewModelScope.launch {
            repository.deleteSchedule(busSchedule)
        }

    }

    fun updateSchedule(busSchedule: BusSchedule){
        viewModelScope.launch {
            repository.updateSchedule(busSchedule)
        }

    }

    fun getAllSchedule() : LiveData<List<BusSchedule>> = repository.getAllSchedules()

    fun getScheduleById(id:Long) =repository.getScheduleById(id)
}