package dataLayer

import uiLayer.AttendanceNC
import java.sql.ResultSet
import java.time.LocalDate
import java.time.LocalDateTime

class AttendanceDL {



    fun attendanceHistByDate(date:LocalDate):ResultSet? {
        try {
            val query1 = "SELECT EXISTS(SELECT * FROM Attendance WHERE Date = '$date') AS res"
            val con = MySql.getConnection()
            val st = con?.createStatement()
            val rs = st?.executeQuery(query1)
            rs?.next()
            if (rs?.getInt("res") == 1) {
                val query = "SELECT * FROM Attendance WHERE Date='$date'"
                return st.executeQuery(query)

            }
            else
            {
                println("The Specific Date is not in the list")
                println()
                return null
            }


        } catch (e: Exception) {
            println("Exception in AttendanceDL : $e")
            return null
        }
    }

    fun attendanceHistByRollNo(studentId :Int):ResultSet? {
        try {
            val query1 = "SELECT EXISTS(SELECT * FROM Attendance WHERE StudentId = $studentId) AS res"
            val con = MySql.getConnection()
            val st = con?.createStatement()
            val rs=st?.executeQuery(query1)
            rs?.next()

            if (rs?.getInt("res") == 1) {

                val query = "SELECT * FROM Attendance WHERE StudentId='$studentId'"
                return st.executeQuery(query)
            }
            else
            {
                println("The specific rollNumber not in the list")
                println()
                return null
            }

        } catch (e: Exception) {
            println("Exception in AttendanceDL : $e")
            return null
        }
    }

    fun validTimeForAttendance():Boolean
    {
        val localDateWithTimeNow=LocalDateTime.now()
        if(localDateWithTimeNow.hour !in 9..12 && localDateWithTimeNow.hour !in 14..16) {
            println("The attendance file only open at 9AM to 1PM and 2PM to 5PM ")
            println()
            return false
        }
        val dateNow=LocalDate.now()
        val con=MySql.getConnection()
        val st=con?.createStatement()

        try {
            val query="SELECT COUNT(Date) from AttendanceCount where date='$dateNow'"
            val rs=st?.executeQuery(query)
            rs?.next()
            if(rs?.getInt("COUNT(Date)")==2)
            {
                println("You Already taken two times today")
                println()
                return false

            }

            else if(rs?.getInt("COUNT(Date)")==1)
            {
                val query2="SELECT timeOfTheDay FROM AttendanceCount WHERE date='$dateNow'"
                val rs1=st.executeQuery(query2)
                rs1.next()
                val timeOfTheDay=rs1.getString("timeOfTheDay")
                val dateWithTime=LocalDateTime.now()


                if(timeOfTheDay.equals("Morning"))  {
                    if(dateWithTime.hour in 14..16)
                    {
                        return true
                    }
                    else {
                        println("You are already Taken attendance")
                        println()
                        return false
                    }
                }
                else
                {
                    println("Come Back Tomorrow")
                    println()
                    return false
                }
            }

        } catch (e:Exception) {
            println("Exception in StudentDetailsDl  : $e")
            return false
        }
        return true
    }
    fun insertAttendance(attendance :AttendanceNC)
    {
        try {

            val query ="insert into Attendance(StudentId,Date,Status) Values(?,?,?)"

            val con=MySql.getConnection()
            val st=con?.prepareStatement(query)
            st?.setInt(1,attendance.studentId)
            st?.setString(2,attendance.date.toString())
            st?.setString(3,attendance.status.toString())
            st?.execute()


        }
        catch (e:Exception)
        {
            println("Exception in AttendanceDL : $e")
        }
    }
    fun insertAttendanceCount()
    {
        val localDate=LocalDateTime.now()
        val timeOfTheDay=if(localDate.hour in 9..12) "Morning" else "Afternoon"
        val query1="INSERT INTO AttendanceCount(date,timeOfTheDay) values('${LocalDate.now()}','$timeOfTheDay')"
        val con=MySql.getConnection()
        val st1=con?.createStatement()
        st1?.execute(query1)
    }





}