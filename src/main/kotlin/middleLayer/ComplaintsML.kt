package middleLayer

import dataLayer.ComplaintsDL
import java.sql.ResultSet

class ComplaintsML {
    private val complaintsDl=ComplaintsDL()
    fun getQueries():ResultSet?=complaintsDl.getComplaints()
    fun deleteQueries()=complaintsDl.clearComplaints()
    fun insertQuery(id:Int,complaint:String)=complaintsDl.insertQuery(id,complaint)
}