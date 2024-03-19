package com.example.busschedule

import android.app.Application
import com.example.busschedule.data.BusScheduleDao
import com.example.busschedule.data.BusScheduleDatabase

class BusScheduleApplication: Application() {
    lateinit var busScheduleDao: BusScheduleDao
    override fun onCreate() {
        super.onCreate()
        busScheduleDao = BusScheduleDatabase.getDatabase(this).busScheduleDao()
    }
}