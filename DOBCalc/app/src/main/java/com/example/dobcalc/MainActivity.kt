package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var selectedDate: TextView? = null
    private var ageInTimes: TextView? = null
    private var ageInMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        selectedDate = findViewById(R.id.selected_date)
        ageInMinutes = findViewById(R.id.age_minutes)
        ageInTimes = findViewById(R.id.age_times)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "You've chosen the date", Toast.LENGTH_SHORT).show()

                val tempSelectedDate = "$selectedYear/${selectedMonth+1}/$selectedDayOfMonth"
                selectedDate!!.text = tempSelectedDate

                val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.KOREA)
                val selectedDateInTimes = sdf.parse(tempSelectedDate)!!.time / (1000 * 60 * 60)
                val currentDateInTimes = sdf.parse(sdf.format(System.currentTimeMillis()))!!.time / (1000 * 60 * 60)
                val differenceOfTimesOfDates = currentDateInTimes - selectedDateInTimes
                ageInTimes!!.text = differenceOfTimesOfDates.toString()

                val selectedDateInMinutes = sdf.parse(tempSelectedDate)!!.time / (1000 * 60)
                val currentDateInMinutes = sdf.parse(sdf.format(System.currentTimeMillis()))!!.time / (1000 * 60)
                val differenceOfMinutesOfDates = currentDateInMinutes - selectedDateInMinutes
                ageInMinutes!!.text = differenceOfMinutesOfDates.toString()
            },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - ( 1000 * 60 * 60 * 24 )
        dpd.show()
    }

}