package middleLayer
import dataLayer.StudentsDetailsDL
import uiLayer.StudentNC
import java.sql.ResultSet

class StudentsDetails {
    private val studentsDetailsDL = StudentsDetailsDL()

    fun rollNoExists(rollNo: String):Boolean = studentsDetailsDL.isRollNoExist(rollNo)
    fun getStudentProfile(rollNo: String): ResultSet? = studentsDetailsDL.getStudentProfile(rollNo)
    fun insertStudent(student: StudentNC) = studentsDetailsDL.insertStudent(student)
    fun getStudentsList(): ResultSet? = studentsDetailsDL.getStudentList()
    fun getStuDetailById(id: Int) = studentsDetailsDL.getStuDetailById(id)
    fun getStuIdByRollNo(rollNo: String) = studentsDetailsDL.getStuIdByRollNo(rollNo)
    fun <T>editStudentDetail(id: Int, columnName: String ,newValue: T) = studentsDetailsDL.editStudent(id, columnName, newValue)
}