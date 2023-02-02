package uiLayer

import java.time.LocalDate

class StudentNC(var rollNo:String, var name:String, var gender : Gender, var phoneNumber : String, var DOB : LocalDate, var age :Int)


enum class Gender
{
   MALE,FEMALE,TRANS
}