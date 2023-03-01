package dataLayer

import uiLayer.StudentNC
import java.sql.ResultSet

class StudentsDetailsDL {
    private val con = MySql.getConnection()

    fun isRollNoExist(rollNo: String): Boolean = try {
            val query = "SELECT EXISTS(SELECT * FROM StudentsDetails WHERE RollNumber = '$rollNo') AS res"

            val st = con?.createStatement()
            val rs = st?.executeQuery(query)
            rs?.next()
            rs?.getInt("res") == 1
        } catch (e: Exception) {
            println("Exception in StudentDetailsDl  : $e")
            false
        }


    fun getStudentProfile(rollNo: String): ResultSet? = try {
            val query = "SELECT * FROM StudentsDetails WHERE RollNumber = '$rollNo'"
            val st = con?.createStatement()
            st?.executeQuery(query)
        } catch (e: Exception) {
            println("Exception in getStudentProfile : $e")
            null
        }


    fun insertStudent(student :StudentNC) = try {
            val query = "INSERT INTO StudentsDetails(RollNumber,Name,Gender,PhoneNumber,DOB,Age) values(?,?,?,?,?,?)"
            val st = con?.prepareStatement(query)
            st?.setString(1, student.rollNo)
            st?.setString(2, student.name)
            st?.setString(3, student.gender.toString())
            st?.setString(4, student.phoneNumber)
            st?.setString(5, student.DOB.toString())
            st?.setInt(6, student.age)
            st?.execute()

            val rs = getStuIdByRollNo(student.rollNo)
            rs?.next()
            val stuId = rs?.getInt("Id")

            val feesDl = FeesDL()
            feesDl.insertNewStu(stuId!!)
            println("Updated")

        } catch (e: Exception) {
            println("Exception in insertStudent : $e")
        }


    fun getStudentList(): ResultSet? = try {
            val query = "SELECT * FROM StudentsDetails"
            val st = con?.createStatement()
            st?.executeQuery(query)
        } catch (e: Exception) {
            println("Exception in getStudentListDL : $e")
            null
        }

    fun getStuDetailById(id: Int): ResultSet? = try {
        val query = "SELECT RollNumber,Name from StudentsDetails where id=$id"
        val st = con?.createStatement()
        st?.executeQuery(query)
    } catch (e: Exception) {
        println("Exception in StudentsDetailsDL : $e")
        null
    }

    fun getStuIdByRollNo(rollNo: String ): ResultSet? = try {
        val query = "SELECT Id from StudentsDetails where RollNumber='$rollNo'"
        val st = con?.createStatement()
        st?.executeQuery(query)
    } catch (e: Exception) {
        println("Exception in StudentsDetailsDL : $e")
        null
    }

    fun <T>editStudent(studentId: Int, columnName: String, newValue: T) = try {
        val query = "UPDATE StudentsDetails SET $columnName = '$newValue' WHERE Id= $studentId"
        val st = con?.createStatement()
        st?.executeUpdate(query)
        println("Successfully Edited")
        println()
    }
    catch (e: Exception) {
        println("Exception in StudentsDetailDl(Edit) : $e")
    }

}