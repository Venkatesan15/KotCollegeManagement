package uiLayer

import java.time.LocalDate

data class StudentNC(val rollNo: String,
                     val name: String,
                     val gender: Gender,
                     val phoneNumber: String,
                     val DOB: LocalDate,
                     val age: Int
)


enum class Gender
{
   MALE,
   FEMALE,
   TRANS
}