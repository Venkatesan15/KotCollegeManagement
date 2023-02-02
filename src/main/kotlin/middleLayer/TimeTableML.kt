package middleLayer

import dataLayer.TimeTableDL
import uiLayer.TimeTableNC
import java.sql.ResultSet

class TimeTableML {
    private val timeTableDl=TimeTableDL()
    fun insert(timeTable : TimeTableNC)=timeTableDl.insert(timeTable)
    fun delete()=timeTableDl.delete()
    fun dayOrderExists(dayOrder : Int)=timeTableDl.dayOrderExists(dayOrder)
    fun editTimeTable(timeTable: TimeTableNC)=timeTableDl.editTimeTable(timeTable)
    fun getCountDayOrder():Int?=timeTableDl.getCountDayOrder()
    fun getTimeTable():ResultSet?=timeTableDl.getTimeTable()
}