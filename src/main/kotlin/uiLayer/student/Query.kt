package uiLayer.student

import middleLayer.ComplaintsML

class Query {
    fun sendQuery(id : Int)
    {
        print("Type Your Complaint/Report : ")
        val complaint= readlnOrNull()
        if(!complaint.isNullOrEmpty())
        {
            val complaintML=ComplaintsML()
            complaintML.insertQuery(id, complaint)
            println("Successfully sent")
        }
        else
        {
            println("Please Type Valid Content ")
            return sendQuery(id)
        }
    }
}