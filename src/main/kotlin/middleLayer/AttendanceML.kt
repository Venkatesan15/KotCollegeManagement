package middleLayer

import dataLayer.AttendanceDL
import uiLayer.AttendanceNC
import java.time.LocalDate

class AttendanceML {
    private val attendance = AttendanceDL()
    fun getHistByDate(date : LocalDate)=attendance.attendanceHistByDate(date)
    fun getHistByRollNo(id :Int)=attendance.attendanceHistByRollNo(id)
    fun validTime():Boolean=attendance.validTimeForAttendance()
    fun insert(attendanceObj :AttendanceNC)=attendance.insertAttendance(attendanceObj)
    fun insertAttendanceCount()=attendance.insertAttendanceCount()
}