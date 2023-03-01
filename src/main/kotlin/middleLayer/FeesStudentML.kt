package middleLayer

import dataLayer.FeesStudentDL
import java.sql.ResultSet

class FeesStudentML {
    private val feeDl = FeesStudentDL()
    fun getUnpaidList(id: Int): ResultSet? = feeDl.showUnpaid(id)
    fun setPaid(id: Int, particular: String) = feeDl.setPaid(id, particular)
}