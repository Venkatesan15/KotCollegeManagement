package uiLayer

import java.time.LocalDate

data class ComplaintNC(val date: LocalDate,
                       val studentId: Int,
                       val complaint: String
)