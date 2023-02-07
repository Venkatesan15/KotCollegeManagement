package uiLayer.admin

import middleLayer.TimeTableML
import uiLayer.TimeTableNC

class TimeTable {
    private val timeTableMl =TimeTableML()
    fun setTimeTable()
    {
        print("No of DayOrders(5,6,7) : ")
        val ui= readln().toIntOrNull()
        var index=1
        if(ui!=null) {
            while (index <= ui) {

                val timeTableObj=getTimeTableObj(index)
                timeTableMl.insert(timeTableObj)
                index++
            }
        }
        else
        {
            println("Please enter valid input")
            return setTimeTable()
        }
    }
    private fun getTimeTableObj(dayOrder:Int):TimeTableNC
        {
            println("Day Order : $dayOrder")
            print("First Period  : ")
            val firstP = readln()
            print("Second Period : ")
            val sP = readln()
            print("Third Period  : ")
            val tP = readln()
            print("Fourth Period : ")
            val fP = readln()
            return TimeTableNC(dayOrder, firstP, sP, tP, fP)
        }

    fun delete() {
        timeTableMl.delete()
        println("Successfully Deleted")
    }
    fun editByDayOrder()
    {
        print("Enter Day Order(<=7) : ")
        val dayOrder:Int? = readln().toIntOrNull()
        if(dayOrder!=null && dayOrder in 1..7)
        {
            if(timeTableMl.dayOrderExists(dayOrder))
            {
                val timeTableObj=getTimeTableObj(dayOrder)
                timeTableMl.editTimeTable(timeTableObj)

                println("Successfully Edited")
                println()
            }
            else
            {
                println("This Day Order Not in the list,So u can't edit")
            }
        }
        else
        {
            println("Please enter valid Input")
        }
    }

    fun addExtraDayOrder()
    {
        val dayOrderCount=timeTableMl.getCountDayOrder()
        if(dayOrderCount!=null && dayOrderCount<7)
        {
            val timeTableObj=getTimeTableObj(dayOrderCount+1)
            timeTableMl.insert(timeTableObj)
            println("Successfully Updated")
            println()
        }
       else println("You can't Add day order above 7 ")
    }
    fun showTimeTable()
    {
        try {
            val timeTable =timeTableMl.getTimeTable()
            var firstP:String?
            var sP:String?
            var tP:String?
            var fP:String?

            if(timeTable!=null)
            {
                System.out.printf("%-10s : %-15s : %-15s : %-15s  :  %-15s","Day Order","First Period","Second Period","Third Period","Fourth Period")
                println()
                while (timeTable.next()) {

                    firstP=timeTable.getString("FirstPeriod")
                    sP=timeTable.getString("SecondPeriod")
                    tP=timeTable.getString("ThirdPeriod")
                    fP=timeTable.getString("FourthPeriod")
                    System.out.printf("%-10s : %-15s : %-15s : %-15s  :  %-15s",timeTable.getInt("DayOrder"),firstP,sP,tP,fP)
                    println()
                }
            }
        }
        catch (e:Exception)
        {
            println("Exception in TimeTable : $e")
        }
    }
}