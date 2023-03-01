package uiLayer.student

import middleLayer.ComplaintsML
import uiLayer.ComplaintNC
import java.time.LocalDate

class Query {
    fun sendQuery(id : Int) {
        print("Type Your Complaint/Report : ")
        val complaint = readlnOrNull()
        if (!complaint.isNullOrEmpty()) {
            val complaintML = ComplaintsML()
            val complaintObj = ComplaintNC(LocalDate.now(), id, complaint)
            complaintML.insertQuery(complaintObj)
            println("Successfully sent")
        }
        else {
            println("Please Type Valid Content ")
            return sendQuery(id)
        }
    }
}
