package uiLayer.admin

import middleLayer.StudentsDetails
import validator.Validator

class EditStudentDetail {
    private val studentDetail = StudentsDetails()
    fun edit(rollNumber :String)
    {
        val validator=Validator()
        if(studentDetail.rollNoExists(rollNumber))
        {
            val idRs=studentDetail.getStuIdByRollNo(rollNumber)
            idRs?.next()
            val studentId=idRs?.getInt("Id")
            var ui:Int?=1
            while(ui in 1..5)
            {
                println("Roll Number --->  1")
                println("Name        --->  2")
                println("Gender      --->  3")
                println("Phone Number--->  4")
                println("DOB         --->  5")
                println("Back        --->  6")
                ui= readln().toIntOrNull()
                if(ui!=null && ui !in 1..6)
                {
                    println("Please Enter valid Input")
                    ui=1
                }
                else
                {
                    when(ui)
                    {
                        1 -> studentDetail.editStudentDetail(studentId!!,"RollNumber",validator.getRollNumber())
                        2 -> studentDetail.editStudentDetail(studentId!!,"Name",validator.getName())
                        3 -> studentDetail.editStudentDetail(studentId!!,"Gender",validator.getGender())
                        4 -> studentDetail.editStudentDetail(studentId!!,"PhoneNumber",validator.getPhoneNumber())
                        5 ->
                        {
                            val dob=validator.getDOB()
                            studentDetail.editStudentDetail(studentId!!,"DOB",dob)
                            studentDetail.editStudentDetail(studentId,"Age",validator.getAge(dob))
                        }
                    }
                }
            }
        }
        else
        {
            println("The roll Number is wrong")
        }
    }
}