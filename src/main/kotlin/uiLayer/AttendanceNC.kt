package uiLayer

import java.time.LocalDate
class AttendanceNC(var studentId:Int, var date:LocalDate, var status:Status)

enum class Status{
    P, A
}