package uiLayer.admin

import middleLayer.AttendanceML
import middleLayer.StudentsDetails
import uiLayer.AttendanceNC
import uiLayer.Status
import java.sql.ResultSet
import java.time.LocalDate

class Attendance {
    private val attendanceML=AttendanceML()
    fun getHistByDate()
    {
        print("Enter Date(yyyy-MM-dd) :  ")
        try {
            val date:LocalDate = LocalDate.parse(readln())
            val rs=attendanceML.getHistByDate(date)
            showHistory(rs)
        }
        catch (e:Exception)
        {
            println("Exception : $e")
            println("Please Enter valid Date format")
            getHistByDate()
        }
    }
    fun getHistByRollNo()
    {
        print("Enter roll Number : ")
        val rollNumber= readln()
        val studentDetail=StudentsDetails()
        val idRs=studentDetail.getStuIdByRollNo(rollNumber)
        idRs?.next()
        val id=idRs?.getInt("Id")
        if(id!=null) showHistory(attendanceML.getHistByRollNo(id))
    }
    private fun showHistory(rs:ResultSet?)
    {
        if(rs!=null)
        {
            System.out.printf("%-15s : %-15s : %-20s : %-2s","RollNumber","Name","Date","Status")
            println()
            val studentsDetails=StudentsDetails()
            while (rs.next())
            {

                val studentId=rs.getInt("StudentId")
                val stuNameAndRoll=studentsDetails.getStuDetailById(studentId)
                stuNameAndRoll?.next()
                val rollNumber=stuNameAndRoll?.getString("RollNumber")
                val name=stuNameAndRoll?.getString("Name")
                val localDate =rs.getDate("Date").toLocalDate()
                val status=Status.valueOf(rs.getString("Status"))

                //val attendanceObj= AttendanceNC(rollNumber,localDate,status)

                System.out.printf("%-15s : %-15s : %-20s : %-2s",rollNumber,name,localDate,status)
                println()
            }
            println()
        }
    }

    fun takeAttendance()
    {
        if(attendanceML.validTime())
        {
            val studentsDetails= StudentsDetails()
            val studentList=studentsDetails.getStudentsList()
            try {
                var rollNumber:String?
                var name:String
                var status:Status
                if(studentList?.next()==false) {
                    println("The Student List is Empty")
                    println()
                    return
                }

                println("Please enter only P or A")
                System.out.printf("%-15s : %-20s : %-2s","RollNumber","Name","Status")
                println()
                do
                {
                    rollNumber = studentList!!.getString("RollNumber")
                    name =studentList.getString("Name")


                    val s=run getStatus@
                    {
                        var ui="dh"
                        while (ui!="P"&&ui!="A") {
                            System.out.printf("%-15s : %-20s : ",rollNumber,name)
                            ui= readln().uppercase()
                            if(ui!="P"&&ui!="A")
                            {
                                println("Please Enter valid Input")
                            }
                        }

                        return@getStatus ui
                    }
                    status= Status.valueOf(s)

                    val idRs=studentsDetails.getStuIdByRollNo(rollNumber)
                    idRs?.next()
                    val studentId=idRs?.getInt("Id")

                    val attendanceObj=AttendanceNC(studentId!!, LocalDate.now(),status)
                    attendanceML.insert(attendanceObj)

                }while(studentList?.next()==true)
                attendanceML.insertAttendanceCount()
                println("Successful")
                println()
            }
            catch (e:Exception)
            {
                println("Exception in AttendanceDL : $e")
            }

        }

    }
}