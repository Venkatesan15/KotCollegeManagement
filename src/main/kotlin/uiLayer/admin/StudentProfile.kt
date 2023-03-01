package uiLayer.admin

import uiLayer.Gender
import uiLayer.StudentNC
import java.sql.ResultSet
import java.time.LocalDate

class StudentProfile {
    fun showStudentProfile(student: ResultSet) {
        var id = 1

        while (student.next()) {
            val rollNumber1 = student.getString("RollNumber")
            val name: String = student.getString("Name")
            val gender = when (student.getString("Gender")) {
                "MALE", "Male" -> Gender.MALE
                "FEMALE", "Female" -> Gender.FEMALE
                "TRANS", "Trans" -> Gender.TRANS
                else -> null
            }
            val phoneNumber: String = student.getString("PhoneNumber")
            val dob: LocalDate = LocalDate.parse(student.getString("DOB"))
            val age = student.getInt("Age")

            val studentObj = StudentNC(rollNumber1, name, gender!!, phoneNumber, dob, age)
            println("Id          :  $id")
            println("Roll Number :  ${studentObj.rollNo}")
            println("Name        :  ${studentObj.name}")
            println("Gender      :  ${studentObj.gender}")
            println("Phone Number : ${studentObj.phoneNumber}")
            println("DOB         :  ${studentObj.DOB}")
            println("Age         :  ${studentObj.age}")
            println()
            id++
        }
        if (id == 1) println("Students Lists is Empty")

    }
}