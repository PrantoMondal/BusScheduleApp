package com.example.actioninputspranto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_schedule")
data class BusSchedule(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val name : String,
    val from : String,
    val to : String,
    @ColumnInfo(name = "departure_date")
    val departureDate : String,
    @ColumnInfo(name = "departure_time")
    val departureTime : String,
    @ColumnInfo(name = "bus_type")
    val busType : String,
    var favorite : Boolean = false,


)
val scheduleList = mutableListOf<BusSchedule>(
    BusSchedule(id =System.currentTimeMillis(), name = "Ena", from = "Dhaka", to = "Rajshahi", departureDate = "22/04/22", departureTime = "23:00", busType = "Economy"),
    BusSchedule(id =System.currentTimeMillis(), name = "Ena", from = "Dhaka", to = "Rajshahi", departureDate = "22/04/22", departureTime = "23:00", busType = "Economy"),
    BusSchedule(id =System.currentTimeMillis(), name = "Ena", from = "Dhaka", to = "Rajshahi", departureDate = "22/04/22", departureTime = "23:00", busType = "Economy"),
    BusSchedule(id =System.currentTimeMillis(), name = "Ena", from = "Dhaka", to = "Rajshahi", departureDate = "22/04/22", departureTime = "23:00", busType = "Economy"),
    BusSchedule(id =System.currentTimeMillis(), name = "Ena", from = "Dhaka", to = "Rajshahi", departureDate = "22/04/22", departureTime = "23:00", busType = "Economy"),
    BusSchedule(id =System.currentTimeMillis(), name = "Ena", from = "Dhaka", to = "Rajshahi", departureDate = "22/04/22", departureTime = "23:00", busType = "Economy"),
    BusSchedule(id =System.currentTimeMillis(), name = "Ena", from = "Dhaka", to = "Rajshahi", departureDate = "22/04/22", departureTime = "23:00", busType = "Economy"),
    BusSchedule(id =System.currentTimeMillis(), name = "Ena", from = "Dhaka", to = "Rajshahi", departureDate = "22/04/22", departureTime = "23:00", busType = "Economy"),
    BusSchedule(id =System.currentTimeMillis(), name = "Ena", from = "Dhaka", to = "Rajshahi", departureDate = "22/04/22", departureTime = "23:00", busType = "Economy"),
    BusSchedule(id =System.currentTimeMillis(), name = "Ena", from = "Dhaka", to = "Rajshahi", departureDate = "22/04/22", departureTime = "23:00", busType = "Economy"),
    BusSchedule(id =System.currentTimeMillis(), name = "Ena", from = "Dhaka", to = "Rajshahi", departureDate = "22/04/22", departureTime = "23:00", busType = "Economy"),
    BusSchedule(id =System.currentTimeMillis(), name = "Ena", from = "Dhaka", to = "Rajshahi", departureDate = "22/04/22", departureTime = "23:00", busType = "Economy"),
    BusSchedule(id =System.currentTimeMillis(), name = "Ena", from = "Dhaka", to = "Rajshahi", departureDate = "22/04/22", departureTime = "23:00", busType = "Economy"),
    BusSchedule(id =System.currentTimeMillis(), name = "Ena", from = "Dhaka", to = "Rajshahi", departureDate = "22/04/22", departureTime = "23:00", busType = "Economy"),
    BusSchedule(id =System.currentTimeMillis(), name = "Ena", from = "Dhaka", to = "Rajshahi", departureDate = "22/04/22", departureTime = "23:00", busType = "Economy"),
)
