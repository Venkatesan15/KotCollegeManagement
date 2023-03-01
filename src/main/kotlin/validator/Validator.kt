package validator

import middleLayer.StudentsDetails
import uiLayer.Gender
import java.time.LocalDate
import java.util.regex.Matcher
import java.util.regex.Pattern

class Validator {
    fun getRollNumber(): String {
        print("Enter Roll Number  : ")
        val rollNumber = readlnOrNull()
        val studentDetails = StudentsDetails()
        return if (!rollNumber.isNullOrEmpty()) {
            if (!studentDetails.rollNoExists(rollNumber))
                rollNumber.uppercase()
            else {
                println("The Roll Number Already Registered")
                getRollNumber()
            }
        } else {
            println("Please Enter valid  RollNumber")
            getRollNumber()
        }
    }
    fun getName(): String
    {
        print("Enter the Name(Only Capital) : ")
        val name = readlnOrNull()
        return if (name!=null) {
            val p: Pattern = Pattern.compile("[A-Z][A-Z\\s]+")
            val m: Matcher = p.matcher(name)
            if (m.matches()) name else {
                println("Please Enter Valid Name ")
                getName()
            }
        } else {
            println("Please enter valid name ")
            getName()
        }
    }
    fun getGender(): Gender
    {
        var ui=0
        while (ui !in 1..3) {

            println("Enter Student Gender(1,2,3) : ")
            println("Male --->  1")
            println("Female --> 2")
            println("Trans ---> 3")
            ui = readln().toIntOrNull()!!
            if(ui !in 1..3)
            {
                println("Please Select valid Gender")
            }
        }
        return when(ui)
        {
            1 -> Gender.MALE
            2 -> Gender.FEMALE
            3 -> Gender.TRANS
            else -> null!!
        }
    }
    fun getPhoneNumber(): String
    {
        print("Enter Phone Number : ")
        val phoneNo = readlnOrNull()
        val p:Pattern = Pattern.compile("[1-9][0-9]{9}")
        val m:Matcher = p.matcher(phoneNo!!)
        return if (m.matches()) phoneNo else {
            println("Please enter valid Phone Number")
            getPhoneNumber()
        }

    }
    fun getDOB(): LocalDate
    {
        print("Enter Date Of Birth(yyyy-MM-dd) : ")
        val dob= readln()
        try {
            val date:LocalDate=LocalDate.parse(dob)
            return if(date.isBefore(LocalDate.now())) date else{
                println("Please enter valid Date Of Birth")
                return getDOB()
            }
        }
        catch (e:Exception)
        {
            println("Please enter valid Date")
            return getDOB()
        }
    }
    fun getAge(dob: LocalDate): Int
    {
        val now =LocalDate.now()
        return now.year - dob.year
    }
}