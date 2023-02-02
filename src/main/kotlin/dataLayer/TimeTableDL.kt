package dataLayer

import uiLayer.TimeTableNC
import java.sql.ResultSet

class TimeTableDL {
    private val con=MySql.getConnection()
    fun insert(timeTable : TimeTableNC)=try {
        val query="INSERT INTO TimeTable values(?,?,?,?,?)"
        val st=con?.prepareStatement(query)
        st?.setInt(1,timeTable.dayOrder)
        st?.setString(2,timeTable.firstP)
        st?.setString(3,timeTable.sP)
        st?.setString(4,timeTable.tP)
        st?.setString(5,timeTable.fP)
        st?.execute()
    }
    catch (e:Exception)
    {
        println("Exception in TimeTableDl : $e")
    }

    fun delete()=try {
        val query="DELETE FROM TimeTable"
        val st=con?.createStatement()
        st?.executeUpdate(query)
    }
    catch (e:Exception)
    {
        println("Exception in TimeTableDl : $e")
    }

    fun dayOrderExists(dayOrder : Int):Boolean=try {
        val query="SELECT EXISTS(SELECT * FROM TimeTable WHERE DayOrder = $dayOrder) AS res"
        val st=con?.createStatement()
        val rs=st?.executeQuery(query)
        rs?.next()
        rs?.getInt("res")==1
    } catch (e:Exception) {
        println("Exception in TimeTableDL  : $e")
        false
    }

    fun editTimeTable(timeTable: TimeTableNC)=
        try {
            val query="update TimeTable Set FirstPeriod='${timeTable.firstP}',SecondPeriod='${timeTable.sP}',ThirdPeriod='${timeTable.tP}',FourthPeriod='${timeTable.fP}' WHERE DayOrder=${timeTable.dayOrder}"
            val st=con?.createStatement()
            st?.executeUpdate(query)
        }
        catch (e:Exception)
        {
            println("Exception in TimeTableDl : $e")
        }

    fun getCountDayOrder():Int?=try {
        val query="SELECT COUNT(DayOrder) from TimeTable"
        val st=con?.createStatement()
        val rs=st?.executeQuery(query)
        rs?.next()
        rs?.getInt("COUNT(DayOrder)")
    }
    catch (e:Exception)
    {
        println("Exception in TimeTableDL : $e")
        null
    }

    fun getTimeTable():ResultSet?=try {
        val query="SELECT * FROM TimeTable"
        val st=con?.createStatement()
        st?.executeQuery(query)
    }
    catch (e:Exception)
    {
        println("Exception in TimeTableDl : $e")
        null
    }
}