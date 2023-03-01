package uiLayer.admin

import middleLayer.ComplaintsML
import middleLayer.StudentsDetails
import java.time.LocalDate

class Complaints {
    private val complaintsML = ComplaintsML()
    fun showComplaints() {
        val studentsDetails = StudentsDetails()
        val rs = complaintsML.getQueries()
        var rollNumber: String?
        var name: String?
        var query: String?
        var date: LocalDate?

        try {
            var c = 0
            while (rs?.next() == true) {
                c++
                val id = rs.getInt("studentId")
                val stuRollAndName = studentsDetails.getStuDetailById(id)
                stuRollAndName?.next()
                rollNumber = stuRollAndName?.getString("RollNumber")
                name = stuRollAndName?.getString("Name")
                query = rs.getString("Query")
                date = LocalDate.parse(rs.getString("Date"))
                println("S.No        :    $c")
                println("Name        :    $name")
                println("Roll Number :    $rollNumber")
                println("Date        :    $date")
                println("Complaint   :    $query")
                println()
            }
        } catch (e: Exception) {
            println("Exception in showComplaints : $e")
        }
    }

    fun clearQueries() = complaintsML.deleteQueries()

}