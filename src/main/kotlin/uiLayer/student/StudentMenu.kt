package uiLayer.student

import middleLayer.StudentsDetails
import uiLayer.admin.AdminDashboard

class StudentMenu {

    fun logIn() {
        print("Enter Your Roll Number : ")
        val rollNumber = readlnOrNull()
        if (!rollNumber.isNullOrEmpty()) {
            val studentDetailsML = StudentsDetails()
            if(studentDetailsML.rollNoExists(rollNumber.uppercase())) {
                menu(rollNumber)
            }
            else {
                println("Roll Number Not in the list")
                val a by lazy {
                    run select@{
                        var ui: Int? = 0
                        while (ui != 1 && ui != 2) {
                            println("Try Again--->1")
                            println("Back--->2")
                            ui = readln().toIntOrNull()

                        }
                        return@select ui
                    }
                }
                if (a == 1) return logIn()

            }
        }
        else {
            println("Please enter valid Format")
            return logIn()
        }
    }

    private fun menu(rollNumber: String) {
        val adminDashboard = AdminDashboard()
        val studentDetails = StudentsDetails()
        val rs = studentDetails.getStuIdByRollNo(rollNumber)
        rs?.next()
        val studentId = rs!!.getInt("Id")
        var ui: Int? = 1
        while (ui in 1..4) {
            println("**************************************")
            println("**     Show Profile---------->1     **")
            println("**     Pay Fees-------------->2     **")
            println("**     Send Query------------>3     **")
            println("**     Show Time Table------->4     **")
            println("**     Exit------------------>5     **")
            println("**************************************")
            ui = readln().toIntOrNull()
            if (ui == null || ui !in 1..5 ) {
                println("Please Enter valid Input")
                ui = 1
            }
            else {
                when(ui) {
                    1 -> adminDashboard.getStudentProfile(rollNumber)

                    2->{
                        val feesStudent = FeesStudent()
                        feesStudent.fees(studentId)
                    }
                    3 -> {
                        val complaint = Query()
                        complaint.sendQuery(studentId)
                    }
                    4 -> adminDashboard.showTimeTable()
                }
            }
        }

    }
}