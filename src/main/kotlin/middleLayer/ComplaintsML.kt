package middleLayer

import dataLayer.ComplaintsDL
import uiLayer.ComplaintNC
import java.sql.ResultSet

class ComplaintsML {
    private val complaintsDl=ComplaintsDL()
    fun getQueries():ResultSet?=complaintsDl.getComplaints()
    fun deleteQueries()=complaintsDl.clearComplaints()
    fun insertQuery(complaintObj : ComplaintNC)=complaintsDl.insertQuery(complaintObj)
}